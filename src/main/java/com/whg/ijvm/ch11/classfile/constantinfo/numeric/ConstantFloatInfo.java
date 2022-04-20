package com.whg.ijvm.ch11.classfile.constantinfo.numeric;

import com.whg.ijvm.ch11.classfile.ClassReader;
import com.whg.ijvm.ch11.classfile.constantinfo.ConstantInfo;

public class ConstantFloatInfo implements ConstantInfo {

    public float val;

    @Override
    public void readInfo(ClassReader reader) {
        val = Float.intBitsToFloat((int)reader.readUint32().value());
    }

}
