package com.whg.ijvm.ch09.instruction.constant;

import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.runtime.RFrame;

public class Nop {
    public static class NOP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {

        }
    }
}
