package com.whg.ijvm.ch11.classfile.constantinfo.method;

import com.whg.ijvm.ch11.classfile.ClassReader;
import com.whg.ijvm.ch11.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch11.classfile.uint.Uint16;

public class MethodTypeInfo implements ConstantInfo {

    Uint16 descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        descriptorIndex = reader.readUint16();
    }

}
