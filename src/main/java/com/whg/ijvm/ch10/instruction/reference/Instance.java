package com.whg.ijvm.ch10.instruction.reference;

import com.whg.ijvm.ch10.heap.RClass;
import com.whg.ijvm.ch10.heap.RConstantPool;
import com.whg.ijvm.ch10.heap.RObject;
import com.whg.ijvm.ch10.heap.constant.ClassRef;
import com.whg.ijvm.ch10.instruction.base.Index16Instruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;

public class Instance {

    public static class INSTANCE_OF extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            RObject ref = stack.popRef();
            if(ref == null){
                stack.pushInt(0);
                return;
            }

            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            ClassRef classRef = cp.getConstant(index.value());
            RClass clazz = classRef.resolveClass();
            if(ref.isInstanceOf(clazz)){
                stack.pushInt(1);
            }else{
                stack.pushInt(0);
            }
        }
    }

    public static class CHECK_CAST extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            RObject ref = stack.popRef();
            stack.pushRef(ref);
            if(ref == null){
                return;
            }

            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            ClassRef classRef = cp.getConstant(index.value());
            RClass clazz = classRef.resolveClass();
            if(!ref.isInstanceOf(clazz)){
                throw new RuntimeException("ClassCastException");
            }
        }
    }

}
