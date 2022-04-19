package com.whg.ijvm.ch10.heap.constant;

import com.whg.ijvm.ch10.classfile.constantinfo.numeric.ConstantFloatInfo;

public class ConstantFloat implements Constant {

    public final float val;

    public ConstantFloat(ConstantFloatInfo info) {
        val = info.val;
    }
}
