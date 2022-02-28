package com.whg.ijvm.ch06.instruction.reference;

import com.whg.ijvm.ch06.heap.RClass;
import com.whg.ijvm.ch06.heap.RConstantPool;
import com.whg.ijvm.ch06.heap.RObject;
import com.whg.ijvm.ch06.heap.constant.ClassRef;
import com.whg.ijvm.ch06.instruction.base.Index16Instruction;
import com.whg.ijvm.ch06.runtime.RFrame;

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
