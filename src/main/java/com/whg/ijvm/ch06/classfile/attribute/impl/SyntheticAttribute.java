package com.whg.ijvm.ch06.classfile.attribute.impl;

import com.whg.ijvm.ch06.classfile.ConstantPool;

public class SyntheticAttribute extends MarkerAttribute{
    public SyntheticAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }
}
