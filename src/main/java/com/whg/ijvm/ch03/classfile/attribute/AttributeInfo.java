package com.whg.ijvm.ch03.classfile.attribute;

import com.whg.ijvm.ch03.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
}
