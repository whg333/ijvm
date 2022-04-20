package com.whg.ijvm.ch11.instruction.control;

import com.whg.ijvm.ch11.instruction.base.BranchInstruction;
import com.whg.ijvm.ch11.instruction.base.BytecodeReader;
import com.whg.ijvm.ch11.runtime.RFrame;

public class LookupSwitch extends BranchInstruction {

    int defaultOffset;
    int npairs;
    int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s(npairs*2);
    }

    @Override
    public void execute(RFrame frame) {
        int key = frame.getOperandStack().popInt();
        for(int i=0;i<npairs*2;i+=2){
            if(matchOffsets[i] == key){
                int offset = matchOffsets[i+1];
                branch(frame, offset);
                return;
            }
        }
        branch(frame, defaultOffset);
    }

}
