package com.whg.ijvm.ch03.classfile.attribute;

import com.whg.ijvm.ch03.classfile.ConstantPool;

public class SyntheticAttribute extends MarkerAttribute{
    SyntheticAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }
}
