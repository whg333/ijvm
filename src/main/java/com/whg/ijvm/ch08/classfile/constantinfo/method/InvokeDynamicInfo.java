package com.whg.ijvm.ch08.classfile.constantinfo.method;

import com.whg.ijvm.ch08.classfile.ClassReader;
import com.whg.ijvm.ch08.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch08.classfile.uint.Uint16;

public class InvokeDynamicInfo implements ConstantInfo {

    Uint16 bootstrapMethodAttrIndex;
    Uint16 nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader reader) {
        bootstrapMethodAttrIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

}
