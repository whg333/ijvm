package com.whg.ijvm.ch10.instruction.conver;

import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;

public class F2x {

    public static class F2D extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushDouble(stack.popFloat());
        }
    }
    public static class F2I extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushInt((int) stack.popFloat());
        }
    }
    public static class F2L extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushLong((long) stack.popFloat());
        }
    }

}
