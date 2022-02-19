package com.whg.ijvm.ch04.classfile.constantinfo;

import com.whg.ijvm.ch04.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
