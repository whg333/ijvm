package com.whg.ijvm.ch06.instruction.stack;

import com.whg.ijvm.ch06.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch06.runtime.OperandStack;
import com.whg.ijvm.ch06.runtime.RFrame;
import com.whg.ijvm.ch06.runtime.Slot;

public class Swap {

    public static class SWAP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            Slot slot1 = stack.popSlot();
            Slot slot2 = stack.popSlot();
            stack.pushSlot(slot1);
            stack.pushSlot(slot2);
        }
    }

}
