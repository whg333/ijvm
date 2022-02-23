package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ClassFile;
import com.whg.ijvm.ch06.classfile.uint.Uint16;
import com.whg.ijvm.ch06.classfile.uint.Uint8;

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

    public RClass(ClassFile cf){
        accessFlags = cf.getAccessFlags();
        name = cf.getClassName();
        superClassName = cf.getSuperClassName();
        interfacesNames = cf.getInterfaceNames();

        constantPool = new RConstantPool(this, cf.getConstantPool());
    }

}
