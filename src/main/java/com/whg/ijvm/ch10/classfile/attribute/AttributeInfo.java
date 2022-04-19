package com.whg.ijvm.ch10.classfile.attribute;

import com.whg.ijvm.ch10.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
    String getName();
}
