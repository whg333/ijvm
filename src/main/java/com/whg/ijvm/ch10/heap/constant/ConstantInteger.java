package com.whg.ijvm.ch10.heap.constant;

import com.whg.ijvm.ch10.classfile.constantinfo.numeric.ConstantIntegerInfo;

public class ConstantInteger implements Constant {

    public final int val;

    public ConstantInteger(ConstantIntegerInfo info) {
        val = info.val;
    }
}
