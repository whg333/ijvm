package com.whg.ijvm.ch06.instruction.constant;

import com.whg.ijvm.ch06.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch06.runtime.RFrame;

public class Const {

    public static class ACONST_NULL extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushRef(null);
        }
    }

    public static class DCONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushDouble(0.0);
        }
    }
    public static class DCONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushDouble(1.0);
        }
    }

    public static class FCONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushFloat(0.0f);
        }
    }
    public static class FCONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushFloat(1.0f);
        }
    }
    public static class FCONST_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushFloat(2.0f);
        }
    }

    public static class ICONST_M1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(-1);
        }
    }
    public static class ICONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(0);
        }
    }
    public static class ICONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(1);
        }
    }
    public static class ICONST_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(2);
        }
    }
    public static class ICONST_3 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(3);
        }
    }
    public static class ICONST_4 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(4);
        }
    }
    public static class ICONST_5 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(5);
        }
    }

    public static class LCONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushLong(0L);
        }
    }
    public static class LCONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushLong(1L);
        }
    }

}
