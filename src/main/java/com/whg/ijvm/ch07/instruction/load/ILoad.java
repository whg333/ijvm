package com.whg.ijvm.ch07.instruction.load;

import com.whg.ijvm.ch07.instruction.base.Index8Instruction;
import com.whg.ijvm.ch07.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch07.runtime.RFrame;

public class ILoad {

    public static class ILOAD extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            iload(frame, index.value());
        }
    }
    public static class ILOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            iload(frame, 0);
        }
    }
    public static class ILOAD_1 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            iload(frame, 1);
        }
    }
    public static class ILOAD_2 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            iload(frame, 2);
        }
    }
    public static class ILOAD_3 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            iload(frame, 3);
        }
    }

    private static void iload(RFrame frame, int index){
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }

}
