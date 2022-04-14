package com.whg.ijvm.ch09.classfile.constantinfo.numeric;

import com.whg.ijvm.ch09.classfile.ClassReader;
import com.whg.ijvm.ch09.classfile.constantinfo.ConstantInfo;

import java.math.BigInteger;

public class ConstantLongInfo implements ConstantInfo {

    public long val;

    @Override
    public void readInfo(ClassReader reader) {
        long upper = reader.readUint32().value();
        long lower = reader.readUint32().value();
        val = unsignedLong(upper, lower);
    }

    public static long unsignedLong(long upper, long lower){
        // Long.toUnsignedBigInteger(1);
        // return (upper << 32) + lower
        return (BigInteger.valueOf(Long.parseLong(Long.toUnsignedString(upper)))).shiftLeft(32)
                .add(BigInteger.valueOf(Long.parseLong(Long.toUnsignedString(lower))))
                .longValue();
    }

}
