package com.whg.ijvm.ch07.classfile.constantinfo;

import com.whg.ijvm.ch07.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
