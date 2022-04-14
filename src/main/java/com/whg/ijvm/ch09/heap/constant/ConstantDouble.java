package com.whg.ijvm.ch09.heap.constant;

import com.whg.ijvm.ch09.classfile.constantinfo.numeric.ConstantDoubleInfo;

public class ConstantDouble implements Constant {

    public final double val;

    public ConstantDouble(ConstantDoubleInfo info) {
        val = info.val;
    }
}
