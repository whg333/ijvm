package com.whg.ijvm.ch11.instruction.reference;

import com.whg.ijvm.ch11.heap.RClass;
import com.whg.ijvm.ch11.heap.RConstantPool;
import com.whg.ijvm.ch11.heap.RObject;
import com.whg.ijvm.ch11.heap.constant.ClassRef;
import com.whg.ijvm.ch11.instruction.base.Index16Instruction;
import com.whg.ijvm.ch11.runtime.RFrame;

public class New {

    public static class NEW extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            ClassRef classRef = cp.getConstant(index.value());
            RClass clazz = classRef.resolveClass();
            if(!clazz.isInit()){
                frame.revertNextPc();
                clazz.init(frame.getThread());
                return;
            }

            if(clazz.isInterface() || clazz.isAbstract()){
                throw new RuntimeException("InstantiationError");
            }

            RObject ref = clazz.newObject();
            frame.getOperandStack().pushRef(ref);
        }
    }

}
