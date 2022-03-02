package com.whg.ijvm.ch07.heap.constant;

import com.whg.ijvm.ch07.classfile.constantinfo.numeric.ConstantDoubleInfo;

public class ConstantDouble implements Constant {

    public final double val;

    public ConstantDouble(ConstantDoubleInfo info) {
        val = info.val;
    }
}
