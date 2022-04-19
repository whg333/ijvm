package com.whg.ijvm.ch10.instruction.constant;

import com.whg.ijvm.ch10.instruction.Instruction;
import com.whg.ijvm.ch10.instruction.base.BytecodeReader;
import com.whg.ijvm.ch10.runtime.RFrame;

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
        @Override
        public String toString() {
            return Instruction.string(this)+" "+val;
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
        @Override
        public String toString() {
            return Instruction.string(this)+" "+val;
        }
    }

}
