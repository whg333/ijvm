package com.whg.ijvm.ch09.classfile.attribute.impl;

import com.whg.ijvm.ch09.classfile.ConstantPool;

public class SyntheticAttribute extends MarkerAttribute{
    public SyntheticAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }
}
