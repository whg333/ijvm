package com.whg.ijvm.ch08.instruction.compare;

import com.whg.ijvm.ch08.instruction.base.BranchInstruction;
import com.whg.ijvm.ch08.runtime.RFrame;

public class IFcond {

    public static class IFEQ extends IFCondInstruction {
        @Override
        boolean cond(int val) {
            return val == 0;
        }
    }
    public static class IFNQ extends IFCondInstruction {
        @Override
        boolean cond(int val) {
            return val != 0;
        }
    }
    public static class IFLT extends IFCondInstruction {
        @Override
        boolean cond(int val) {
            return val < 0;
        }
    }
    public static class IFLE extends IFCondInstruction {
        @Override
        boolean cond(int val) {
            return val <= 0;
        }
    }
    public static class IFGT extends IFCondInstruction {
        @Override
        boolean cond(int val) {
            return val > 0;
        }
    }
    public static class IFGE extends IFCondInstruction {
        @Override
        boolean cond(int val) {
            return val >= 0;
        }
    }

    private static abstract class IFCondInstruction extends BranchInstruction{
        @Override
        public void execute(RFrame frame) {
            int val =  frame.getOperandStack().popInt();
            if(cond(val)){
                branch(frame, offset);
            }
        }

        abstract boolean cond(int val);

    }

}
