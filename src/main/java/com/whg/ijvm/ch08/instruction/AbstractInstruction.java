package com.whg.ijvm.ch08.instruction;

import com.whg.ijvm.ch08.instruction.base.BytecodeReader;
import com.whg.ijvm.ch08.runtime.RFrame;

public abstract class AbstractInstruction implements Instruction{

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(RFrame frame) {

    }

    @Override
    public String toString() {
        return Instruction.string(this);
    }

}
