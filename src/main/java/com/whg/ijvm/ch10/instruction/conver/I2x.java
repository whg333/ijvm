package com.whg.ijvm.ch10.instruction.conver;

import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;

public class I2x {

    public static class I2F extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushFloat(stack.popInt());
        }
    }
    public static class I2D extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushDouble(stack.popInt());
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
