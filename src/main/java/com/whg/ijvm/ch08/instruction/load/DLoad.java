package com.whg.ijvm.ch08.instruction.load;

import com.whg.ijvm.ch08.instruction.base.Index8Instruction;
import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.RFrame;

public class DLoad {

    public static class DLOAD extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            dload(frame, index.value());
        }
    }
    public static class DLOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            dload(frame, 0);
        }
    }
    public static class DLOAD_1 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            dload(frame, 1);
        }
    }
    public static class DLOAD_2 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            dload(frame, 2);
        }
    }
    public static class DLOAD_3 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            dload(frame, 3);
        }
    }

    private static void dload(RFrame frame, int index){
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }

}
