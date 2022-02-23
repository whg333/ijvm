package com.whg.ijvm.ch06.instruction.constant;

import com.whg.ijvm.ch06.instruction.Instruction;
import com.whg.ijvm.ch06.instruction.base.BytecodeReader;
import com.whg.ijvm.ch06.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch06.runtime.RFrame;

public class Ipush {

    public static class BIPUSH implements Instruction {
        byte val;
        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readInt8();
        }
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(val);
        }
    }

    public static class SIPUSH implements Instruction {
        short val;
        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readInt16();
        }
        @Override
        public void execute(RFrame frame) {
            frame.getOperandStack().pushInt(val);
        }
    }

}
