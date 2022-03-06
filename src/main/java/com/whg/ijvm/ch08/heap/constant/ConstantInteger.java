package com.whg.ijvm.ch08.heap.constant;

import com.whg.ijvm.ch08.classfile.constantinfo.numeric.ConstantIntegerInfo;

public class ConstantInteger implements Constant {

    public final int val;

    public ConstantInteger(ConstantIntegerInfo info) {
        val = info.val;
    }
}
