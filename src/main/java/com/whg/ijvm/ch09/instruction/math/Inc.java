package com.whg.ijvm.ch09.instruction.math;

import com.whg.ijvm.ch09.classfile.uint.Uint8;
import com.whg.ijvm.ch09.instruction.Instruction;
import com.whg.ijvm.ch09.instruction.base.BytecodeReader;
import com.whg.ijvm.ch09.runtime.LocalVars;
import com.whg.ijvm.ch09.runtime.RFrame;

public class Inc {

    public static class IINC implements Instruction{

        short index;
        int iConst;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readUint8().value();
            iConst = reader.readInt8();
        }

        @Override
        public void execute(RFrame frame) {
            LocalVars localVars = frame.getLocalVars();
            int val = localVars.getInt(index);
            val += iConst;
            localVars.setInt(index, val);
        }

        @Override
        public String toString() {
            return Instruction.string(this)+" "+index+" by "+iConst;
        }
    }

}
