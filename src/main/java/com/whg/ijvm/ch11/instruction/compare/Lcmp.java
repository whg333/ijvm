package com.whg.ijvm.ch11.instruction.compare;

import com.whg.ijvm.ch11.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch11.runtime.OperandStack;
import com.whg.ijvm.ch11.runtime.RFrame;

public class Lcmp {

    public static class LCMP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            if(v1 > v2){
                stack.pushInt(1);
            } else if(v1 == v2){
                stack.pushInt(0);
            } else {
                stack.pushInt(-1);
            }
        }
    }

}
