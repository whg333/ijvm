package com.whg.ijvm.ch06.instruction.base;

import com.whg.ijvm.ch06.classfile.uint.Uint8;
import com.whg.ijvm.ch06.instruction.AbstractInstruction;

public class Index8Instruction extends AbstractInstruction {

    protected Uint8 index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint8();
    }
}
