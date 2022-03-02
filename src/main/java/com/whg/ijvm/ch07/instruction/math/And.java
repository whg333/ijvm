package com.whg.ijvm.ch07.instruction.math;

import com.whg.ijvm.ch07.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class And {

    public static class IAND extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int result = v1 & v2;
            stack.pushInt(result);
        }
    }
    public static class LAND extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            long result = v1 & v2;
            stack.pushLong(result);
        }
    }

}
