package com.whg.ijvm.ch03.classfile.attribute.impl;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.attribute.AbstractAttribute;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class ConstantValueAttribute extends AbstractAttribute {

    private Uint16 constantValueIndex;

    public ConstantValueAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        constantValueIndex = reader.readUint16();
    }

    public Uint16 getConstantValueIndex(){
        return constantValueIndex;
    }

}
