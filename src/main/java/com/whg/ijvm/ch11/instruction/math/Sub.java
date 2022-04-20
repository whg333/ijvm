package com.whg.ijvm.ch11.instruction.math;

import com.whg.ijvm.ch11.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch11.runtime.OperandStack;
import com.whg.ijvm.ch11.runtime.RFrame;

public class Sub {

    public static class ISUB extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int result = v1 - v2;
            stack.pushInt(result);
        }
    }
    public static class LSUB extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            long result = v1 - v2;
            stack.pushLong(result);
        }
    }
    public static class FSUB extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            float v2 = stack.popFloat();
            float v1 = stack.popFloat();
            float result = v1 - v2;
            stack.pushFloat(result);
        }
    }
    public static class DSUB extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            double v2 = stack.popDouble();
            double v1 = stack.popDouble();
            double result = v1 - v2;
            stack.pushDouble(result);
        }
    }

}
