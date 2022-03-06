package com.whg.ijvm.ch08.heap.constant;

import com.whg.ijvm.ch08.classfile.constantinfo.string.ConstantStringInfo;

public class ConstantString implements Constant {

    public final String val;

    public ConstantString(ConstantStringInfo info) {
        val = info.getString();
    }
}
