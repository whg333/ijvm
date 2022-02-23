package com.whg.ijvm.ch06.instruction;

import com.whg.ijvm.ch06.instruction.base.BytecodeReader;
import com.whg.ijvm.ch06.runtime.RFrame;

public interface Instruction {

    void fetchOperands(BytecodeReader reader);

    void execute(RFrame frame);

}
