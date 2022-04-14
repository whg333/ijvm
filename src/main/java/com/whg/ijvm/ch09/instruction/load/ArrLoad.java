package com.whg.ijvm.ch09.instruction.load;

import com.whg.ijvm.ch09.heap.RArray;
import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.runtime.OperandStack;
import com.whg.ijvm.ch09.runtime.RFrame;

import java.util.Objects;

public class ArrLoad {

    public static class AALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            RObject[] refs = arrRef.getRefs();
            RArray.checkIndex(refs.length, index);
            stack.pushRef(refs[index]);
        }
    }

    public static class BALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            byte[] refs = arrRef.getBytes();
            RArray.checkIndex(refs.length, index);
            stack.pushInt(refs[index]);
        }
    }

    public static class CALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            char[] refs = arrRef.getChars();
            RArray.checkIndex(refs.length, index);
            stack.pushInt(refs[index]);
        }
    }

    public static class DALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            double[] refs = arrRef.getDoubles();
            RArray.checkIndex(refs.length, index);
            stack.pushDouble(refs[index]);
        }
    }

    public static class FALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            float[] refs = arrRef.getFloats();
            RArray.checkIndex(refs.length, index);
            stack.pushFloat(refs[index]);
        }
    }

    public static class IALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            int[] refs = arrRef.getInts();
            RArray.checkIndex(refs.length, index);
            stack.pushInt(refs[index]);
        }
    }

    public static class LALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            long[] refs = arrRef.getLongs();
            RArray.checkIndex(refs.length, index);
            stack.pushLong(refs[index]);
        }
    }

    public static class SALOAD extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            short[] refs = arrRef.getShorts();
            RArray.checkIndex(refs.length, index);
            stack.pushInt(refs[index]);
        }
    }

}
