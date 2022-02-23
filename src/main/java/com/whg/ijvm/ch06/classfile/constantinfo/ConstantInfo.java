package com.whg.ijvm.ch06.classfile.constantinfo;

import com.whg.ijvm.ch06.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
