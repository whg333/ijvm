package com.whg.ijvm.ch05.instruction.conver;

import com.whg.ijvm.ch05.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch05.runtime.OperandStack;
import com.whg.ijvm.ch05.runtime.RFrame;

public class D2x {

    public static class D2F extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushFloat((float) stack.popDouble());
        }
    }
    public static class D2I extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushInt((int) stack.popDouble());
        }
    }
    public static class D2L extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushLong((long) stack.popDouble());
        }
    }

}
