package com.whg.ijvm.ch07.heap;

import com.whg.ijvm.ch07.classfile.ClassFile;
import com.whg.ijvm.ch07.heap.constant.*;

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

    public RClass(ClassFile cf, RClassLoader loader){
        accessFlags = cf.getAccessFlags().value();
        name = cf.getClassName();
        superClassName = cf.getSuperClassName();
        interfacesNames = cf.getInterfaceNames();

        constantPool = new RConstantPool(this, cf.getConstantPool());
        fields = RField.newFields(this, cf);
        methods = RMethod.newMethods(this, cf);

        this.loader = loader;
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
                initStaticFinalVar(this, field);
            }
        }
    }

    private void initStaticFinalVar(RClass clazz, RField field) {
        Slots vars = clazz.staticVars;
        RConstantPool cp = clazz.constantPool;
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
                case "Ljava/lang/String":
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
        RClass c = superClass;
        while(c != null){
            if(c == other){
                return true;
            }
            c = c.superClass;
        }
        return false;
    }

    public RObject newObject(){
        return new RObject(this);
    }

    public RMethod getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    RMethod getStaticMethod(String name, String descriptor){
        for(RMethod method: methods){
            if(method.isStatic() && method.isMatch(name, descriptor)){
                return method;
            }
        }
        return null;
    }

    public boolean isAssignableFrom(RClass other) {
        if(this == other){
            return true;
        }
        if(this.isInterface()){
            return other.isImplememnts(this);
        }else{
            return other.isSubClassOf(this);
        }
    }

    private boolean isImplememnts(RClass iface) {
        RClass c = this;
        while(c != null){
            for(RClass i: c.getInterfaces()){
                if(i == iface || i.isSubInterfaceOf(iface)){
                    return true;
                }
            }
            c = c.superClass;
        }
        return false;
    }

    private boolean isSubInterfaceOf(RClass iface) {
        for(RClass superInterface: interfaces){
            if(superInterface == iface || superInterface.isSubInterfaceOf(iface)){
                return true;
            }
        }
        return false;
    }

    /** setter/getter */
    public RField[] getFields() {
        return fields;
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

}
