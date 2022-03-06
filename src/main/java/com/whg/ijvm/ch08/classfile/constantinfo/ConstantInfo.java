package com.whg.ijvm.ch08.classfile.constantinfo;

import com.whg.ijvm.ch08.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
