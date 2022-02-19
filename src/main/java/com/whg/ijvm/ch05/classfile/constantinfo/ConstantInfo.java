package com.whg.ijvm.ch05.classfile.constantinfo;

import com.whg.ijvm.ch05.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
