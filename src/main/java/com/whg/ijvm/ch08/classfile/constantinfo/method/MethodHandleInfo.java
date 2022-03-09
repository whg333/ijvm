package com.whg.ijvm.ch08.classfile.constantinfo.method;

import com.whg.ijvm.ch08.classfile.ClassReader;
import com.whg.ijvm.ch08.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch08.classfile.uint.Uint16;
import com.whg.ijvm.ch08.classfile.uint.Uint8;

public class MethodHandleInfo implements ConstantInfo {

    Uint8 referenceKind;
    Uint16 referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        referenceKind = reader.readUint8();
        referenceIndex = reader.readUint16();
    }

}
