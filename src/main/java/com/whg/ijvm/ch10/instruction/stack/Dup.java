package com.whg.ijvm.ch10.instruction.stack;

import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;
import com.whg.ijvm.ch10.runtime.Slot;

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

    public static class DUP_X1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            Slot slot1 = stack.popSlot();
            Slot slot2 = stack.popSlot();
            stack.pushSlot(slot1);
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
        }
    }

}
