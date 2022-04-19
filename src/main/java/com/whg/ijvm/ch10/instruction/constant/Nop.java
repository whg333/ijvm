package com.whg.ijvm.ch10.instruction.constant;

import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.RFrame;

public class Nop {
    public static class NOP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {

        }
    }
}
