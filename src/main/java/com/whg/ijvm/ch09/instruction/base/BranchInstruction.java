package com.whg.ijvm.ch09.instruction.base;

import com.whg.ijvm.ch09.instruction.AbstractInstruction;
import com.whg.ijvm.ch09.runtime.RFrame;

public class BranchInstruction extends AbstractInstruction {

    protected int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt16();
    }

    protected void branch(RFrame frame, int offset){
        int nextPc = frame.getPc() + offset;
        frame.setNextPc(nextPc);
    }

    @Override
    public String toString() {
        return super.toString()+" "+(offset > 0 ? "+" : "")+offset;
    }

}
