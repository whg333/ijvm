package com.whg.ijvm.ch05.instruction.extend;

import com.whg.ijvm.ch05.instruction.base.BranchInstruction;
import com.whg.ijvm.ch05.runtime.RFrame;
import com.whg.ijvm.ch05.runtime.RObject;

public class IFnull {

    public static class IFNULL extends IFnullInstruction{
        @Override
        boolean cond(RObject ref) {
            return ref == null;
        }
    }
    public static class IFNONNULL extends IFnullInstruction{
        @Override
        boolean cond(RObject ref) {
            return ref != null;
        }
    }

    private static abstract class IFnullInstruction extends BranchInstruction {
        @Override
        public void execute(RFrame frame) {
            RObject ref = frame.getOperandStack().popRef();
            if(cond(ref)){
                branch(frame, offset);
            }
        }

        abstract boolean cond(RObject ref);

    }

}
