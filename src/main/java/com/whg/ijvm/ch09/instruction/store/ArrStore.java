package com.whg.ijvm.ch09.instruction.store;

import com.whg.ijvm.ch09.heap.RArray;
import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.runtime.OperandStack;
import com.whg.ijvm.ch09.runtime.RFrame;

import java.util.Objects;

public class ArrStore {

    public static class AASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            RObject val = stack.popRef();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            RObject[] array = arrRef.getRefs();
            RArray.checkIndex(array.length, index);
            array[index] = val;
        }
    }

    public static class BASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            byte[] array = arrRef.getBytes();
            RArray.checkIndex(array.length, index);
            array[index] = (byte) val;
        }
    }

    public static class CASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            char[] array = arrRef.getChars();
            RArray.checkIndex(array.length, index);
            array[index] = (char) val;
        }
    }

    public static class DASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            double val = stack.popDouble();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            double[] array = arrRef.getDoubles();
            RArray.checkIndex(array.length, index);
            array[index] = val;
        }
    }

    public static class FASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            float val = stack.popFloat();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            float[] array = arrRef.getFloats();
            RArray.checkIndex(array.length, index);
            array[index] = val;
        }
    }

    public static class IASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            int[] array = arrRef.getInts();
            RArray.checkIndex(array.length, index);
            array[index] = val;
        }
    }

    public static class LASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long val = stack.popLong();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            long[] array = arrRef.getLongs();
            RArray.checkIndex(array.length, index);
            array[index] = val;
        }
    }

    public static class SASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            short[] array = arrRef.getShorts();
            RArray.checkIndex(array.length, index);
            array[index] = (short) val;
        }
    }

}
