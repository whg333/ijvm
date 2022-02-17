package com.whg.ijvm.ch03.classfile.constantinfo;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class NameAndTypeInfo implements ConstantInfo {

    public Uint16 nameIndex;
    public Uint16 descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }

}
