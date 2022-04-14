package com.whg.ijvm.ch09.instruction.store;

import com.whg.ijvm.ch09.instruction.base.Index8Instruction;
import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.runtime.RFrame;

public class DStore {

    public static class DSTORE extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            dstore(frame, index.value());
        }
    }
    public static class DSTORE_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            dstore(frame, 0);
        }
    }
    public static class DSTORE_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            dstore(frame, 1);
        }
    }
    public static class DSTORE_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            dstore(frame, 2);
        }
    }
    public static class DSTORE_3 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            dstore(frame, 3);
        }
    }

    private static void dstore(RFrame frame, int index){
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

}
