package com.whg.ijvm.ch08.instruction.store;

import com.whg.ijvm.ch08.instruction.base.Index8Instruction;
import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.RFrame;

public class LStore {

    public static class LSTORE extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            lstore(frame, index.value());
        }
    }
    public static class LSTORE_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            lstore(frame, 0);
        }
    }
    public static class LSTORE_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            lstore(frame, 1);
        }
    }
    public static class LSTORE_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            lstore(frame, 2);
        }
    }
    public static class LSTORE_3 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            lstore(frame, 3);
        }
    }

    private static void lstore(RFrame frame, int index){
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }

}
