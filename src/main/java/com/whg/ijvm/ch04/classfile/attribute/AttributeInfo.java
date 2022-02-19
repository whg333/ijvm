package com.whg.ijvm.ch04.classfile.attribute;

import com.whg.ijvm.ch04.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
    String getName();
}
