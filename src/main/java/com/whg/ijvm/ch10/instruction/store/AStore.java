package com.whg.ijvm.ch10.instruction.store;

import com.whg.ijvm.ch10.heap.RObject;
import com.whg.ijvm.ch10.instruction.base.Index8Instruction;
import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.runtime.RFrame;

public class AStore {

    public static class ASTORE extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            astore(frame, index.value());
        }
    }
    public static class ASTORE_0 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            astore(frame, 0);
        }
    }
    public static class ASTORE_1 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            astore(frame, 1);
        }
    }
    public static class ASTORE_2 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            astore(frame, 2);
        }
    }
    public static class ASTORE_3 extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            astore(frame, 3);
        }
    }

    //操作数栈 --> 局部变量表
    private static void astore(RFrame frame, int index){
        RObject ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }

}
