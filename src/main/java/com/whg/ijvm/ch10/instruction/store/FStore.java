package com.whg.ijvm.ch10.instruction.store;

import com.whg.ijvm.ch10.instruction.base.Index8Instruction;
import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.RFrame;

public class FStore {

    public static class FSTORE extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            fstore(frame, index.value());
        }
    }
    public static class FSTORE_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            fstore(frame, 0);
        }
    }
    public static class FSTORE_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            fstore(frame, 1);
        }
    }
    public static class FSTORE_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            fstore(frame, 2);
        }
    }
    public static class FSTORE_3 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            fstore(frame, 3);
        }
    }

    private static void fstore(RFrame frame, int index){
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }

}
