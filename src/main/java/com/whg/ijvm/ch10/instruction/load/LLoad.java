package com.whg.ijvm.ch10.instruction.load;

import com.whg.ijvm.ch10.instruction.base.Index8Instruction;
import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.RFrame;

public class LLoad {

    public static class LLOAD extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            lload(frame, index.value());
        }
    }
    public static class LLOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            lload(frame, 0);
        }
    }
    public static class LLOAD_1 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            lload(frame, 1);
        }
    }
    public static class LLOAD_2 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            lload(frame, 2);
        }
    }
    public static class LLOAD_3 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            lload(frame, 3);
        }
    }

    private static void lload(RFrame frame, int index){
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }

}
