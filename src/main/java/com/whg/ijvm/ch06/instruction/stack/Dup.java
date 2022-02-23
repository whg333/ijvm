package com.whg.ijvm.ch06.instruction.stack;

import com.whg.ijvm.ch06.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch06.runtime.OperandStack;
import com.whg.ijvm.ch06.runtime.RFrame;
import com.whg.ijvm.ch06.runtime.Slot;

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
