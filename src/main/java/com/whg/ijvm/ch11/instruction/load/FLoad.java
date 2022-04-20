package com.whg.ijvm.ch11.instruction.load;

import com.whg.ijvm.ch11.instruction.base.Index8Instruction;
import com.whg.ijvm.ch11.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch11.runtime.RFrame;

public class FLoad {

    public static class FLOAD extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            fload(frame, index.value());
        }
    }
    public static class FLOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            fload(frame, 0);
        }
    }
    public static class FLOAD_1 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            fload(frame, 1);
        }
    }
    public static class FLOAD_2 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            fload(frame, 2);
        }
    }
    public static class FLOAD_3 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            fload(frame, 3);
        }
    }

    private static void fload(RFrame frame, int index){
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }

}
