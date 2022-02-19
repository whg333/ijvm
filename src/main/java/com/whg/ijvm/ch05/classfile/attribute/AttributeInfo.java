package com.whg.ijvm.ch05.classfile.attribute;

import com.whg.ijvm.ch05.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
    String getName();
}
