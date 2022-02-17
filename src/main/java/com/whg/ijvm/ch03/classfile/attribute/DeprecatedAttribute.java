package com.whg.ijvm.ch03.classfile.attribute;

import com.whg.ijvm.ch03.classfile.ConstantPool;

public class DeprecatedAttribute extends MarkerAttribute{
    DeprecatedAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }
}
