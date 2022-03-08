package com.whg.ijvm.ch08.instruction.load;

import com.whg.ijvm.ch08.heap.RArray;
import com.whg.ijvm.ch08.heap.RObject;
import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.OperandStack;
import com.whg.ijvm.ch08.runtime.RFrame;

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

}
