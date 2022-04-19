package com.whg.ijvm.ch10.classfile.constantinfo.method;

import com.whg.ijvm.ch10.classfile.ClassReader;
import com.whg.ijvm.ch10.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch10.classfile.uint.Uint16;

public class MethodTypeInfo implements ConstantInfo {

    Uint16 descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        descriptorIndex = reader.readUint16();
    }

}
