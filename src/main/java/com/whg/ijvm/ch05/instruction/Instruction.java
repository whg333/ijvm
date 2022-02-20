package com.whg.ijvm.ch05.instruction;

import com.whg.ijvm.ch05.instruction.base.BytecodeReader;
import com.whg.ijvm.ch05.runtime.RFrame;

public interface Instruction {

    void fetchOperands(BytecodeReader reader);

    void execute(RFrame frame);

}
