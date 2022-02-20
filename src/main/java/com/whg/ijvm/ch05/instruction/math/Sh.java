package com.whg.ijvm.ch05.instruction.math;

import com.whg.ijvm.ch05.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch05.runtime.OperandStack;
import com.whg.ijvm.ch05.runtime.RFrame;

public class Sh {

    public static class ISHL extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int s = v2 & 0x1f; //取前5位
            int result = v1 << s;
            stack.pushInt(result);
        }
    }
    public static class ISHR extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int s = v2 & 0x1f; //取前5位
            int result = v1 >> s;
            stack.pushInt(result);
        }
    }
    public static class IUSHR extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            int s = v2 & 0x1f; //取前5位
            int result = v1 >>> s;
            stack.pushInt(result);
        }
    }

    public static class LSHL extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            long v1 = stack.popLong();
            int s = v2 & 0x3f; //取前6位
            long result = v1 << s;
            stack.pushLong(result);
        }
    }
    public static class LSHR extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            long v1 = stack.popLong();
            int s = v2 & 0x3f; //取前6位
            long result = v1 >> s;
            stack.pushLong(result);
        }
    }
    public static class LUSHR extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            long v1 = stack.popLong();
            int s = v2 & 0x3f; //取前6位
            long result = v1 >>> s;
            stack.pushLong(result);
        }
    }

}