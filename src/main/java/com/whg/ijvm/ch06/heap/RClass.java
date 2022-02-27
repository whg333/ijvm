package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ClassFile;
import com.whg.ijvm.ch06.classfile.uint.Uint16;
import com.whg.ijvm.ch06.classfile.uint.Uint8;

import static com.whg.ijvm.ch06.heap.AccessFlags.*;

public class RClass {

    Uint16 accessFlags;
    String name;
    String superClassName;
    String[] interfacesNames;

    RConstantPool constantPool;
    RField[] fields;
    RMethod[] methods;

    RClassLoader loader;
    RClass superClass;
    RClass[] interfaces;

    Uint8 instanceSlotCount;
    Uint8 staticSlotCount;
    Slots staticVars;

    public RClass(ClassFile cf, RClassLoader loader){
        accessFlags = cf.getAccessFlags();
        name = cf.getClassName();
        superClassName = cf.getSuperClassName();
        interfacesNames = cf.getInterfaceNames();

        constantPool = new RConstantPool(this, cf.getConstantPool());
        fields = RField.newFields(this, cf);
        methods = RMethod.newMethods(this, cf);

        this.loader = loader;
        resolveSuperClass();
        resolveInterfaces();
    }

    private void resolveSuperClass(){
        if(!name.equals("java/lang/Object")){
            superClass = loader.loadClass(superClassName);
        }
    }

    private void resolveInterfaces(){
        interfaces = new RClass[interfacesNames.length];
        for(int i=0;i<interfacesNames.length;i++){
            interfaces[i] = loader.loadClass(interfacesNames[i]);
        }
    }

    public boolean isPublic(){
        return (accessFlags() & ACC_PUBLIC) != 0;
    }
    public boolean isFinal(){
        return (accessFlags() & ACC_FINAL) != 0;
    }
    public boolean isSuper(){
        return (accessFlags() & ACC_SUPER) != 0;
    }
    public boolean isInterface(){
        return (accessFlags() & ACC_INTERFACE) != 0;
    }
    public boolean isAbstract(){
        return (accessFlags() & ACC_ABSTRACT) != 0;
    }
    public boolean isSynthetic(){
        return (accessFlags() & ACC_SYNTHETIC) != 0;
    }
    public boolean isAnnotation(){
        return (accessFlags() & ACC_ANNOTATION) != 0;
    }
    public boolean isEnum(){
        return (accessFlags() & ACC_ENUM) != 0;
    }

    private int accessFlags(){
        return accessFlags.value();
    }

}
