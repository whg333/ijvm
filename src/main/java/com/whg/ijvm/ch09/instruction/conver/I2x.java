package com.whg.ijvm.ch09.instruction.conver;

import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.runtime.OperandStack;
import com.whg.ijvm.ch09.runtime.RFrame;

public class I2x {

    public static class I2F extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushFloat((float) stack.popInt());
        }
    }
    public static class I2I extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushInt(stack.popInt());
        }
    }
    public static class I2L extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushLong(stack.popInt());
        }
    }

}
