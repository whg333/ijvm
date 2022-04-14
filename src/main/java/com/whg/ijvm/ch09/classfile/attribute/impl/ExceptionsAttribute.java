package com.whg.ijvm.ch09.classfile.attribute.impl;

import com.whg.ijvm.ch09.classfile.ClassReader;
import com.whg.ijvm.ch09.classfile.ConstantPool;
import com.whg.ijvm.ch09.classfile.attribute.AbstractAttribute;
import com.whg.ijvm.ch09.classfile.uint.Uint16;

public class ExceptionsAttribute extends AbstractAttribute {

    Uint16[] exceptionIndexTable;

    public ExceptionsAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s();
    }
}
