package com.whg.ijvm.ch10.instruction.math;

import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;

public class Xor {

    public static class IXOR extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int result = v1 ^ v2;
            stack.pushInt(result);
        }
    }
    public static class LXOR extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            long result = v1 ^ v2;
            stack.pushLong(result);
        }
    }

}
