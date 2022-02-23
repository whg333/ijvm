package com.whg.ijvm.ch06.instruction.stack;

import com.whg.ijvm.ch06.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch06.runtime.OperandStack;
import com.whg.ijvm.ch06.runtime.RFrame;

public class Pop {

    public static class POP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.popSlot();
        }
    }

    public static class POP2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.popSlot();
            stack.popSlot();
        }
    }

}