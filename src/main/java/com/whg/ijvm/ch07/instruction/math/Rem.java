package com.whg.ijvm.ch07.instruction.math;

import com.whg.ijvm.ch07.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Rem {

    public static class IREM extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            if(v2 == 0){
                throw new ArithmeticException("/ by zero");
            }
            int result = v1 % v2;
            stack.pushInt(result);
        }
    }
    public static class LREM extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            if(v2 == 0){
                throw new ArithmeticException("/ by zero");
            }
            long result = v1 % v2;
            stack.pushLong(result);
        }
    }

    public static class FREM extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            float v2 = stack.popFloat();
            float v1 = stack.popFloat();
            float result = v1 % v2;
            stack.pushFloat(result);
        }
    }
    public static class DREM extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            double v2 = stack.popDouble();
            double v1 = stack.popDouble();
            double result = v1 % v2;
            stack.pushDouble(result);
        }
    }

}
