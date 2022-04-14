package com.whg.ijvm.ch09.classfile.constantinfo;

import com.whg.ijvm.ch09.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
