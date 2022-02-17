package com.whg.ijvm.ch03.classfile.attribute;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;

public class MarkerAttribute extends AbstractAttribute{

    MarkerAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {

    }

}
