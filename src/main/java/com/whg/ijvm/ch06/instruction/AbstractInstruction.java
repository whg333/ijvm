package com.whg.ijvm.ch06.instruction;

import com.whg.ijvm.ch06.instruction.base.BytecodeReader;
import com.whg.ijvm.ch06.runtime.RFrame;

public abstract class AbstractInstruction implements Instruction{

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(RFrame frame) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
