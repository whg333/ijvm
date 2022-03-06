package com.whg.ijvm.ch08.instruction.control;

import com.whg.ijvm.ch08.instruction.base.BranchInstruction;
import com.whg.ijvm.ch08.instruction.base.BytecodeReader;
import com.whg.ijvm.ch08.runtime.RFrame;

public class TableSwitch extends BranchInstruction {

    int defaultOffset;
    int low;
    int high;
    int[] jumpOffsets;


    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
        int jumpOffsetsCount = high - low + 1;
        jumpOffsets = reader.readInt32s(jumpOffsetsCount);
    }

    @Override
    public void execute(RFrame frame) {
        int index = frame.getOperandStack().popInt();
        int offset = defaultOffset;
        if(index >= low && index <= high){
            offset = jumpOffsets[index];
        }
        branch(frame, offset);
    }

}
