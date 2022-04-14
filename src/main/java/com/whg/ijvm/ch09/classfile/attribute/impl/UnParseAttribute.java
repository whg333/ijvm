package com.whg.ijvm.ch09.classfile.attribute.impl;

import com.whg.ijvm.ch09.classfile.ClassReader;
import com.whg.ijvm.ch09.classfile.ConstantPool;
import com.whg.ijvm.ch09.classfile.attribute.AbstractAttribute;

public class UnParseAttribute extends AbstractAttribute {

    byte[] info;

    public UnParseAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        info = reader.readBytes(length);
    }

}
