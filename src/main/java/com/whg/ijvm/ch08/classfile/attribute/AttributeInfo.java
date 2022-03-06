package com.whg.ijvm.ch08.classfile.attribute;

import com.whg.ijvm.ch08.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
    String getName();
}
