package com.whg.ijvm.ch06.classfile.attribute.impl;

import com.whg.ijvm.ch06.classfile.ClassReader;
import com.whg.ijvm.ch06.classfile.ConstantPool;
import com.whg.ijvm.ch06.classfile.attribute.AbstractAttribute;

public class MarkerAttribute extends AbstractAttribute {

    MarkerAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {

    }

}
