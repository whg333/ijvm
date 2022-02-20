package com.whg.ijvm.ch05.instruction.stack;

import com.whg.ijvm.ch05.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch05.runtime.OperandStack;
import com.whg.ijvm.ch05.runtime.RFrame;
import com.whg.ijvm.ch05.runtime.Slot;

public class Dup {

    public static class DUP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            Slot slot = stack.popSlot();
            stack.pushSlot(slot);
            stack.pushSlot(slot);
        }
    }

}
