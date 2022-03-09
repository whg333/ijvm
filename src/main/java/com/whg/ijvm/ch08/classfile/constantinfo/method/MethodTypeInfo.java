package com.whg.ijvm.ch08.classfile.constantinfo.method;

import com.whg.ijvm.ch08.classfile.ClassReader;
import com.whg.ijvm.ch08.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch08.classfile.uint.Uint16;

public class MethodTypeInfo implements ConstantInfo {

    Uint16 descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        descriptorIndex = reader.readUint16();
    }

}
