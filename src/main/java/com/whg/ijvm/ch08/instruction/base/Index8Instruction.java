package com.whg.ijvm.ch08.instruction.base;

import com.whg.ijvm.ch08.classfile.uint.Uint8;
import com.whg.ijvm.ch08.instruction.AbstractInstruction;

public class Index8Instruction extends AbstractInstruction {

    protected Uint8 index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint8();
    }

    @Override
    public String toString() {
        return super.toString()+" #"+index.value();
    }

}
