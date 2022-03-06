package com.whg.ijvm.ch07.instruction.constant;

import com.whg.ijvm.ch07.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Nop {
    public static class NOP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {

        }
    }
}
