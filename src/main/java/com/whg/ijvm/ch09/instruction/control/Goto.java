package com.whg.ijvm.ch09.instruction.control;

import com.whg.ijvm.ch09.instruction.base.BranchInstruction;
import com.whg.ijvm.ch09.runtime.RFrame;

public class Goto extends BranchInstruction {
    @Override
    public void execute(RFrame frame) {
        branch(frame, offset);
    }
}
