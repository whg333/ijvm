package com.whg.ijvm.ch06.classfile.attribute.table;

import com.whg.ijvm.ch06.classfile.uint.Uint16;

public class ExceptionTableEntry {

    Uint16 startPc;
    Uint16 endPc;
    Uint16 handlerPc;
    Uint16 catchType;

    public ExceptionTableEntry(Uint16 startPc, Uint16 endPc, Uint16 handlerPc, Uint16 catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

}
