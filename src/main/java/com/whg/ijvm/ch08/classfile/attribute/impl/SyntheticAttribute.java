package com.whg.ijvm.ch08.classfile.attribute.impl;

import com.whg.ijvm.ch08.classfile.ConstantPool;

public class SyntheticAttribute extends MarkerAttribute{
    public SyntheticAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }
}
