package com.whg.ijvm.ch06.instruction.compare;

import com.whg.ijvm.ch06.instruction.base.BranchInstruction;
import com.whg.ijvm.ch06.runtime.OperandStack;
import com.whg.ijvm.ch06.runtime.RFrame;

public class IFIcmp {

    public static class IF_ICMPEQ extends IFIcmpInstruction{
        @Override
        boolean cond(int val1, int val2) {
            return val1 == val2;
        }
    }
    public static class IF_ICMPNE extends IFIcmpInstruction{
        @Override
        boolean cond(int val1, int val2) {
            return val1 != val2;
        }
    }
    public static class IF_ICMPLT extends IFIcmpInstruction{
        @Override
        boolean cond(int val1, int val2) {
            return val1 < val2;
        }
    }
    public static class IF_ICMPLE extends IFIcmpInstruction{
        @Override
        boolean cond(int val1, int val2) {
            return val1 <= val2;
        }
    }
    public static class IF_ICMPGT extends IFIcmpInstruction{
        @Override
        boolean cond(int val1, int val2) {
            return val1 > val2;
        }
    }
    public static class IF_ICMPGE extends IFIcmpInstruction{
        @Override
        boolean cond(int val1, int val2) {
            return val1 >= val2;
        }
    }

    private static abstract class IFIcmpInstruction extends BranchInstruction {
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val2 =  stack.popInt();
            int val1 =  stack.popInt();
            if(cond(val1, val2)){
                branch(frame, offset);
            }
        }

        abstract boolean cond(int val1, int val2);

    }

}
