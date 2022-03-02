package com.whg.ijvm.ch07.instruction.reference;

import com.whg.ijvm.ch07.heap.RClass;
import com.whg.ijvm.ch07.heap.RConstantPool;
import com.whg.ijvm.ch07.heap.RObject;
import com.whg.ijvm.ch07.heap.constant.ClassRef;
import com.whg.ijvm.ch07.instruction.base.Index16Instruction;
import com.whg.ijvm.ch07.runtime.RFrame;

public class New {

    public static class NEW extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            ClassRef classRef = cp.getConstant(index.value());
            RClass clazz = classRef.resolveClass();

            if(clazz.isInterface() || clazz.isAbstract()){
                throw new RuntimeException("InstantiationError");
            }

            RObject ref = clazz.newObject();
            frame.getOperandStack().pushRef(ref);
        }
    }

}
