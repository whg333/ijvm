package com.whg.ijvm.ch07.instruction.stack;

import com.whg.ijvm.ch07.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;
import com.whg.ijvm.ch07.runtime.Slot;

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
