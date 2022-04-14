package com.whg.ijvm.ch09.instruction.constant;

import com.whg.ijvm.ch09.heap.RClass;
import com.whg.ijvm.ch09.heap.RConstantPool;
import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.heap.StringPool;
import com.whg.ijvm.ch09.heap.constant.*;
import com.whg.ijvm.ch09.instruction.base.Index16Instruction;
import com.whg.ijvm.ch09.instruction.base.Index8Instruction;
import com.whg.ijvm.ch09.runtime.OperandStack;
import com.whg.ijvm.ch09.runtime.RFrame;

public class Ldc {

    public static class LDC extends Index8Instruction{
        @Override
        public void execute(RFrame frame) {
            _ldc(frame, index.value());
        }
    }
    public static class LDC_W extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            _ldc(frame, index.value());
        }
    }

    private static void _ldc(RFrame frame, int index){
        OperandStack stack = frame.getOperandStack();
        RClass clazz = frame.getMethod().getRClass();
        RConstantPool cp = clazz.getRConstantPool();
        Constant c = cp.getConstant(index);

        if(c instanceof ConstantInteger){
            stack.pushInt(((ConstantInteger) c).val);
        }else if(c instanceof ConstantFloat){
            stack.pushFloat(((ConstantFloat) c).val);
        }else if(c instanceof ConstantString){
            RObject internedStr = StringPool.JString(clazz.loader, ((ConstantString) c).val);
            stack.pushRef(internedStr);
        } else{
            throw new RuntimeException("todo: ldc!");
        }
    }

    public static class LDC2_W extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            Constant c = cp.getConstant(index.value());

            if(c instanceof ConstantLong){
                stack.pushLong(((ConstantLong) c).val);
            }else if(c instanceof ConstantDouble){
                stack.pushDouble(((ConstantDouble) c).val);
            }else{
                throw new RuntimeException("todo: ldc!");
            }
        }
    }

}
