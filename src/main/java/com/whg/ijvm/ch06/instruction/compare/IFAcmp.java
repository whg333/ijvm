package com.whg.ijvm.ch06.instruction.compare;

import com.whg.ijvm.ch06.instruction.base.BranchInstruction;
import com.whg.ijvm.ch06.runtime.OperandStack;
import com.whg.ijvm.ch06.runtime.RFrame;
import com.whg.ijvm.ch06.heap.RObject;

public class IFAcmp {

    public static class IF_ACMPEQ extends IFAcmpInstruction{
        @Override
        boolean cond(RObject ref1, RObject ref2) {
            return ref1 == ref2;
        }
    }
    public static class IF_ACMPNE extends IFAcmpInstruction{
        @Override
        boolean cond(RObject ref1, RObject ref2) {
            return ref1 != ref2;
        }
    }

    private static abstract class IFAcmpInstruction extends BranchInstruction {
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            RObject ref2 =  stack.popRef();
            RObject ref1 =  stack.popRef();
            if(cond(ref1, ref2)){
                branch(frame, offset);
            }
        }

        abstract boolean cond(RObject ref1, RObject ref2);

    }

}
