package com.whg.ijvm.ch11.instruction.extend;

import com.whg.ijvm.ch11.instruction.base.BranchInstruction;
import com.whg.ijvm.ch11.runtime.RFrame;
import com.whg.ijvm.ch11.heap.RObject;

public class IfNull {

    public static class IFNULL extends IfNullInstruction{
        @Override
        boolean cond(RObject ref) {
            return ref == null;
        }
    }
    public static class IFNONNULL extends IfNullInstruction{
        @Override
        boolean cond(RObject ref) {
            return ref != null;
        }
    }

    private static abstract class IfNullInstruction extends BranchInstruction {
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
