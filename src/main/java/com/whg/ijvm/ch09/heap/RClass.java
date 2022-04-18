package com.whg.ijvm.ch09.heap;

import com.whg.ijvm.ch09.classfile.ClassFile;
import com.whg.ijvm.ch09.heap.constant.*;
import com.whg.ijvm.ch09.runtime.RFrame;
import com.whg.ijvm.ch09.runtime.RThread;

public class RClass {

    int accessFlags;
    String name;
    String superClassName;
    String[] interfacesNames;

    RConstantPool constantPool;
    RField[] fields;
    RMethod[] methods;

    public RClassLoader loader;
    RClass superClass;
    RClass[] interfaces;

    int instanceSlotCount;
    int staticSlotCount;
    Slots staticVars;

    boolean init; //类是否已经加载

    RObject classObj; // 类到对象的关联：Class -> Class Object，指明此类对应的类对象，即java.lang.Class实例

    RClass(){

    }

    public RClass(ClassFile cf, RClassLoader loader){
        this.loader = loader;

        accessFlags = cf.getAccessFlags().value();
        name = cf.getClassName();
        superClassName = cf.getSuperClassName();
        interfacesNames = cf.getInterfaceNames();

        constantPool = new RConstantPool(this, cf.getConstantPool());
        fields = RField.newFields(this, cf);
        methods = RMethod.newMethods(this, cf);
    }

    public void resolveSuperClass(){
        if(!name.equals("java/lang/Object")){
            superClass = loader.loadClass(superClassName);
        }
    }

    public void resolveInterfaces(){
        interfaces = new RClass[interfacesNames.length];
        for(int i=0;i<interfacesNames.length;i++){
            interfaces[i] = loader.loadClass(interfacesNames[i]);
        }
    }

    public void calcInstanceFieldSlotIds() {
        int slotId = 0;
        if(superClass != null){
            slotId = superClass.instanceSlotCount;
        }
        for(RField field : fields){
            if(!field.isStatic()){
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()){
                    slotId++;
                }
            }
        }
        instanceSlotCount = slotId;
    }

    public void calcStaticFieldSlotIds(){
        int slotId = 0;
        for(RField field : fields){
            if(field.isStatic()){
                field.slotId = slotId;
                slotId++;
                if(field.isLongOrDouble()){
                    slotId++;
                }
            }
        }
        staticSlotCount = slotId;
    }

    public void allocAndInitStaticVars(){
        staticVars = new Slots(staticSlotCount);
        for(RField field: fields){
            if(field.isStatic() && field.isFinal()){
                initStaticFinalVar(field);
            }
        }
    }

    private void initStaticFinalVar(RField field) {
        Slots vars = staticVars;
        RConstantPool cp = constantPool;
        int cpIndex = field.constValueIndex;
        int slotId = field.slotId;

        if(cpIndex > 0){
            switch (field.getDescriptor()){
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    ConstantInteger cInteger = cp.getConstant(cpIndex);
                    vars.setInt(slotId, cInteger.val);
                    break;
                case "J":
                    ConstantLong cLong = cp.getConstant(cpIndex);
                    vars.setLong(slotId, cLong.val);
                    break;
                case "F":
                    ConstantFloat cFloat = cp.getConstant(cpIndex);
                    vars.setFloat(slotId, cFloat.val);
                    break;
                case "D":
                    ConstantDouble cDouble = cp.getConstant(cpIndex);
                    vars.setDouble(slotId, cDouble.val);
                    break;
                case "Ljava/lang/String;":
                    ConstantString cStr = cp.getConstant(cpIndex);
                    RObject jStr = StringPool.JString(loader, cStr.val);
                    vars.setRef(slotId, jStr);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown descriptor="+field.getDescriptor());
            }
        }
    }

    public boolean isPublic(){
        return AccessFlags.isPublic(accessFlags);
    }
    public boolean isFinal(){
        return AccessFlags.isFinal(accessFlags);
    }
    public boolean isSuper(){
        return AccessFlags.isSuper(accessFlags);
    }
    public boolean isInterface(){
        return AccessFlags.isInterface(accessFlags);
    }
    public boolean isAbstract(){
        return AccessFlags.isAbstract(accessFlags);
    }
    public boolean isSynthetic(){
        return AccessFlags.isSynthetic(accessFlags);
    }
    public boolean isAnnotation(){
        return AccessFlags.isAnnotation(accessFlags);
    }
    public boolean isEnum(){
        return AccessFlags.isEnum(accessFlags);
    }

    public String getName() {
        return name;
    }

    public String getSimpleName(){
        return name.substring(name.lastIndexOf('/') + 1);
    }

    // java/lang/Object -> java.lang.Object
    public String getJavaName(){
        return name.replaceAll("/","\\.");
    }

    public boolean isAccessibleTo(RClass other) {
        return isPublic() || isSamePackage(other);
    }

    public boolean isSamePackage(RClass other){
        return getPackageName().equals(other.getPackageName());
    }

    private String getPackageName() {
        int index = name.lastIndexOf('/');
        if(index >= 0){
            return name.substring(0, index);
        }
        return "";
    }

    // self extends c
    public boolean isSubClassOf(RClass other) {
        for(RClass c = superClass; c != null; c = c.superClass){
            if(c == other){
                return true;
            }
        }
        return false;
    }

    // c extends self
    public boolean isSuperClassOf(RClass other){
        return other.isSubClassOf(this);
    }

    // iface extends self
    public boolean isSuperInterfaceOf(RClass iface){
        return iface.isSubInterfaceOf(this);
    }

    // self implements iface
    public boolean isImplements(RClass iface){
        for(RClass c = this; c != null; c = c.superClass){
            for(RClass i: c.interfaces){
                if(i == iface || i.isSubInterfaceOf(iface)){
                    return true;
                }
            }
        }
        return false;
    }

    // self extends iface
    public boolean isSubInterfaceOf(RClass iface){
        for(RClass superInterface: interfaces){
            if(superInterface == iface || superInterface.isSubInterfaceOf(iface)){
                return true;
            }
        }
        return false;
    }

    public RArray newArray(int count){
        if(!isArray()){
            throw new IllegalArgumentException("Not array class: "+name);
        }
        return new RArray(this, name, count);
    }

    public boolean isArray(){
        return isArray(name);
    }

    public static boolean isArray(String className){
        return className.charAt(0) == '[';
    }

    public RClass getArrayClass(){
        String arrayClassName = RArray.getArrayClassName(name);
        return loader.loadClass(arrayClassName);
    }

    public RObject newObject(){
        return new RObject(this);
    }

    public RMethod getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    RMethod getStaticMethod(String name, String descriptor){
        return getMethod(name, descriptor, true);
    }

    public RMethod getInstanceMethod(String name, String descriptor){
        return getMethod(name, descriptor, false);
    }

    RMethod getMethod(String name, String descriptor, boolean isStatic){
        for(RMethod method: methods){
            if(method.isStatic() == isStatic && method.isMatch(name, descriptor)){
                return method;
            }
        }
        return null;
    }

    public boolean isAssignableFrom(RClass other) {
        RClass s = other, t = this;
        if(s == t){
            return true;
        }
        if(!s.isArray()){
            if(!s.isInterface()){
                if(!t.isInterface()){
                    return s.isSubClassOf(t);
                }else{
                    return s.isImplements(t);
                }
            }else{
                if(t.isInterface()){
                    return t.isJlObject();
                }else{
                    return t.isSuperInterfaceOf(s);
                }
            }
        }else{
            if(!t.isArray()){
                if(!t.isInterface()){
                    return t.isJlObject();
                }else{
                    return t.isJlCloneable() || t.isJioSerializable();
                }
            }else{
                RClass sc = s.getComponentClass();
                RClass tc = t.getComponentClass();
                return sc == tc || tc.isAssignableFrom(sc);
            }
        }
    }

    boolean isJlObject() {
        return name.equals("java/lang/Object");
    }
    boolean isJlCloneable() {
        return name.equals("java/lang/Cloneable");
    }
    boolean isJioSerializable() {
        return name.equals("java/io/Serializable");
    }

    public boolean isInit() {
        return init;
    }

    public void init(RThread thread){
        init = true;
        scheduleClinit(thread);
        initSuperClass(thread);
    }

    private void scheduleClinit(RThread thread) {
        RMethod clinitMethod = getClinitMethod();
        if(clinitMethod != null){
            // exec <clinit>
            RFrame newFrame = thread.newFrame(clinitMethod);
            thread.pushFrame(newFrame);
        }
    }

    private RMethod getClinitMethod() {
        return getStaticMethod("<clinit>", "()V");
    }

    private void initSuperClass(RThread thread) {
        if(!isInterface()){
            if(superClass != null && !superClass.isInit()){
                superClass.init(thread);
            }
        }
    }

    // for array
    public RClass getComponentClass() {
        String componentClassName = RArray.getComponentClassName(name);
        return loader.loadClass(componentClassName);
    }

    // reflection
    public void setRefVar(String name, String descriptor, RObject ref) {
        RField field = getField(name, descriptor, true);
        staticVars.setRef(field.slotId, ref);
    }
    // reflection
    public RObject getRefVar(String name, String descriptor) {
        RField field = getField(name, descriptor, true);
        return staticVars.getRef(field.slotId);
    }

    public RField getField(String name, String descriptor, boolean isStatic) {
        for(RClass c=this; c!=null; c=c.superClass){
            for(RField field: c.fields){
                if(field.isStatic() == isStatic
                        && field.isMatch(name, descriptor)){
                    return field;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    /** setter/getter */
    public RField[] getFields() {
        return fields;
    }

    public RMethod[] getMethods() {
        return methods;
    }

    public RClass[] getInterfaces() {
        return interfaces;
    }

    public RClass getSuperClass() {
        return superClass;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public RConstantPool getRConstantPool() {
        return constantPool;
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public void setJClass(RObject jClass) {
        this.classObj = jClass;
    }
    public RObject getJClass() {
        return classObj;
    }

    public boolean isPrimitive() {
        return RArray.isPrimitive(getName());
    }
}
