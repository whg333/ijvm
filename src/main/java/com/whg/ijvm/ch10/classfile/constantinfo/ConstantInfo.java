package com.whg.ijvm.ch10.classfile.constantinfo;

import com.whg.ijvm.ch10.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
