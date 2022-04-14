package com.whg.ijvm.ch09.heap.constant;

import com.whg.ijvm.ch09.classfile.constantinfo.numeric.ConstantIntegerInfo;

public class ConstantInteger implements Constant {

    public final int val;

    public ConstantInteger(ConstantIntegerInfo info) {
        val = info.val;
    }
}
