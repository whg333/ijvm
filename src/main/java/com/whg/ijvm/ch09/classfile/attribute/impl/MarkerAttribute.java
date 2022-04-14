package com.whg.ijvm.ch09.classfile.attribute.impl;

import com.whg.ijvm.ch09.classfile.ClassReader;
import com.whg.ijvm.ch09.classfile.ConstantPool;
import com.whg.ijvm.ch09.classfile.attribute.AbstractAttribute;

public class MarkerAttribute extends AbstractAttribute {

    MarkerAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {

    }

}
