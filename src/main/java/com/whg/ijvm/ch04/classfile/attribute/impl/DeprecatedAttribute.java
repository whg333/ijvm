package com.whg.ijvm.ch04.classfile.attribute.impl;

import com.whg.ijvm.ch04.classfile.ConstantPool;

public class DeprecatedAttribute extends MarkerAttribute{
    public DeprecatedAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }
}
