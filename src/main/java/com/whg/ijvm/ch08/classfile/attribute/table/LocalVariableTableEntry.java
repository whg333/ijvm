package com.whg.ijvm.ch08.classfile.attribute.table;

import com.whg.ijvm.ch08.classfile.uint.Uint16;

public class LocalVariableTableEntry {

    Uint16 startPc;
    Uint16 length;
    Uint16 nameIndex;
    Uint16 descriptorIndex;
    Uint16 index;

    public LocalVariableTableEntry(Uint16 startPc, Uint16 length, Uint16 nameIndex, Uint16 descriptorIndex, Uint16 index) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.index = index;
    }

}
