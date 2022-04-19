package com.whg.ijvm.ch10.instruction.math;

import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;

public class Add {

    public static class IADD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int result = v1 + v2;
            stack.pushInt(result);
        }
    }
    public static class LADD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            long result = v1 + v2;
            stack.pushLong(result);
        }
    }
    public static class FADD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            float v2 = stack.popFloat();
            float v1 = stack.popFloat();
            float result = v1 + v2;
            stack.pushFloat(result);
        }
    }
    public static class DADD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            double v2 = stack.popDouble();
            double v1 = stack.popDouble();
            double result = v1 + v2;
            stack.pushDouble(result);
        }
    }

}
