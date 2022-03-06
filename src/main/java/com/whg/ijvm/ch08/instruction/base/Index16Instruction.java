package com.whg.ijvm.ch08.instruction.base;

import com.whg.ijvm.ch08.classfile.uint.Uint16;
import com.whg.ijvm.ch08.instruction.AbstractInstruction;

public class Index16Instruction extends AbstractInstruction {

    protected Uint16 index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();
    }

    @Override
    public String toString() {
        return super.toString()+" #"+index.value();
    }

}
