package com.whg.ijvm.ch08.classfile.constantinfo.numeric;

import com.whg.ijvm.ch08.classfile.ClassReader;
import com.whg.ijvm.ch08.classfile.constantinfo.ConstantInfo;

public class ConstantDoubleInfo implements ConstantInfo {

    public double val;

    @Override
    public void readInfo(ClassReader reader) {
        long upper = reader.readUint32().value();
        long lower = reader.readUint32().value();
        long unsignedLong = ConstantLongInfo.unsignedLong(upper, lower);
        val = Double.longBitsToDouble(unsignedLong);
    }
}
