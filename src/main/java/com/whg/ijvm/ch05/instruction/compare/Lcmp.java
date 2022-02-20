package com.whg.ijvm.ch05.instruction.compare;

import com.whg.ijvm.ch05.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch05.runtime.OperandStack;
import com.whg.ijvm.ch05.runtime.RFrame;

public class Lcmp {

    public static class LCMP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
        }
    }

}
