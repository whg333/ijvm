package com.whg.ijvm.ch11.instruction.constant;

import com.whg.ijvm.ch11.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch11.runtime.RFrame;

public class Nop {
    public static class NOP extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {

        }
    }
}
