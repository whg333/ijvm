package com.whg.ijvm.ch11.classfile.constantinfo;

import com.whg.ijvm.ch11.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
