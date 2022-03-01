package com.whg.ijvm.ch06.instruction.load;

import com.whg.ijvm.ch06.heap.RObject;
import com.whg.ijvm.ch06.instruction.base.Index8Instruction;
import com.whg.ijvm.ch06.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch06.runtime.RFrame;

public class ALoad {

    public static class ALOAD extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            aload(frame, index.value());
        }
    }
    public static class ALOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            aload(frame, 0);
        }
    }
    public static class ALOAD_1 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            aload(frame, 1);
        }
    }
    public static class ALOAD_2 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            aload(frame, 2);
        }
    }
    public static class ALOAD_3 extends NoOperandsInstruction {
        @Override
        public void execute(RFrame frame) {
            aload(frame, 3);
        }
    }

    //局部变量表 --> 操作数栈
    private static void aload(RFrame frame, int index){
        RObject ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }

}
