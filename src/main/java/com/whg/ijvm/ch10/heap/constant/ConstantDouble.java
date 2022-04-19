package com.whg.ijvm.ch10.heap.constant;

import com.whg.ijvm.ch10.classfile.constantinfo.numeric.ConstantDoubleInfo;

public class ConstantDouble implements Constant {

    public final double val;

    public ConstantDouble(ConstantDoubleInfo info) {
        val = info.val;
    }
}
