package com.whg.ijvm.ch10.heap.constant;

import com.whg.ijvm.ch10.classfile.constantinfo.string.ConstantStringInfo;

public class ConstantString implements Constant {

    public final String val;

    public ConstantString(ConstantStringInfo info) {
        val = info.getString();
    }
}
