package com.whg.ijvm.ch09.classfile.attribute;

import com.whg.ijvm.ch09.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
    String getName();
}
