package com.whg.ijvm.ch08.instruction.store;

import com.whg.ijvm.ch08.heap.RArray;
import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.OperandStack;
import com.whg.ijvm.ch08.runtime.RFrame;

import java.util.Objects;

public class ArrStore {

    public static class IASTORE extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            RArray arrRef = stack.popArrRef();

            Objects.requireNonNull(arrRef);
            int[] ints = arrRef.getInts();
            RArray.checkIndex(ints.length, index);
            ints[index] = val;
        }
    }

}
