package com.whg.ijvm.ch10.classfile.constantinfo.numeric;

import com.whg.ijvm.ch10.classfile.ClassReader;
import com.whg.ijvm.ch10.classfile.constantinfo.ConstantInfo;

public class ConstantFloatInfo implements ConstantInfo {

    public float val;

    @Override
    public void readInfo(ClassReader reader) {
        val = Float.intBitsToFloat((int)reader.readUint32().value());
    }

}
