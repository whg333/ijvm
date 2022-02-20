package com.whg.ijvm.ch05.instruction;

import com.whg.ijvm.ch05.instruction.base.BytecodeReader;
import com.whg.ijvm.ch05.runtime.RFrame;

public abstract class AbstractInstruction implements Instruction{
    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(RFrame frame) {

    }
}
