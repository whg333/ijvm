package com.whg.ijvm.ch08.instruction.constant;

import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.RFrame;

public class Nop {
    public static class NOP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {

        }
    }
}
