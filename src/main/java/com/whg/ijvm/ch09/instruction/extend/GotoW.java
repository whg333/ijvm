package com.whg.ijvm.ch09.instruction.extend;

import com.whg.ijvm.ch09.instruction.base.BranchInstruction;
import com.whg.ijvm.ch09.instruction.base.BytecodeReader;
import com.whg.ijvm.ch09.runtime.RFrame;

public class GotoW extends BranchInstruction {
    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt32();
    }

    @Override
    public void execute(RFrame frame) {
        branch(frame, offset);
    }
}
