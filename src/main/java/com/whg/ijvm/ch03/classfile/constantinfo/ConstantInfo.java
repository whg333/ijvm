package com.whg.ijvm.ch03.classfile.constantinfo;

import com.whg.ijvm.ch03.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
