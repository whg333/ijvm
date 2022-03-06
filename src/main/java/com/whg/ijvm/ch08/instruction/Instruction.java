package com.whg.ijvm.ch08.instruction;

import com.whg.ijvm.ch08.instruction.base.BytecodeReader;
import com.whg.ijvm.ch08.runtime.RFrame;

public interface Instruction {

    void fetchOperands(BytecodeReader reader);

    void execute(RFrame frame);

    static String string(Instruction inst){
        return inst.getClass().getSimpleName();
    }

}
