package com.whg.ijvm.ch11.instruction.control;

import com.whg.ijvm.ch11.heap.RObject;
import com.whg.ijvm.ch11.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch11.runtime.RFrame;
import com.whg.ijvm.ch11.runtime.RThread;

public class Return {

    public static class RETURN extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            frame.getThread().popFrame();
        }
    }
    public static class ARETURN extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            RThread thread = frame.getThread();
            RFrame currentFrame = thread.popFrame();
            RFrame invokerFrame = thread.topFrame();
            RObject retVal = currentFrame.getOperandStack().popRef();
            invokerFrame.getOperandStack().pushRef(retVal);
        }
    }
    public static class IRETURN extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            RThread thread = frame.getThread();
            RFrame currentFrame = thread.popFrame();
            RFrame invokerFrame = thread.topFrame();
            int retVal = currentFrame.getOperandStack().popInt();
            invokerFrame.getOperandStack().pushInt(retVal);
        }
    }
    public static class LRETURN extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            RThread thread = frame.getThread();
            RFrame currentFrame = thread.popFrame();
            RFrame invokerFrame = thread.topFrame();
            long retVal = currentFrame.getOperandStack().popLong();
            invokerFrame.getOperandStack().pushLong(retVal);
        }
    }
    public static class FRETURN extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            RThread thread = frame.getThread();
            RFrame currentFrame = thread.popFrame();
            RFrame invokerFrame = thread.topFrame();
            float retVal = currentFrame.getOperandStack().popFloat();
            invokerFrame.getOperandStack().pushFloat(retVal);
        }
    }
    public static class DRETURN extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            RThread thread = frame.getThread();
            RFrame currentFrame = thread.popFrame();
            RFrame invokerFrame = thread.topFrame();
            double retVal = currentFrame.getOperandStack().popDouble();
            invokerFrame.getOperandStack().pushDouble(retVal);
        }
    }

}
