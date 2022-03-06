package com.whg.ijvm.ch08.instruction.store;

import com.whg.ijvm.ch08.instruction.base.Index8Instruction;
import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.RFrame;

public class IStore {

    public static class ISTORE extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            istore(frame, index.value());
        }
    }
    public static class ISTORE_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            istore(frame, 0);
        }
    }
    public static class ISTORE_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            istore(frame, 1);
        }
    }
    public static class ISTORE_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            istore(frame, 2);
        }
    }
    public static class ISTORE_3 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            istore(frame, 3);
        }
    }

    private static void istore(RFrame frame, int index){
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }

}
