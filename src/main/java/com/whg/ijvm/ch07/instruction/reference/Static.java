package com.whg.ijvm.ch07.instruction.reference;

import com.whg.ijvm.ch07.heap.*;
import com.whg.ijvm.ch07.heap.constant.FieldRef;
import com.whg.ijvm.ch07.instruction.base.Index16Instruction;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Static {

    public static class PUT_STATIC extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RMethod currentMethod = frame.getMethod();
            RClass currentClazz = currentMethod.getRClass();
            RConstantPool cp = currentClazz.getRConstantPool();
            FieldRef fieldRef = cp.getConstant(index.value());
            RField field = fieldRef.resolveField();
            RClass clazz = field.getRClass();

            if(!field.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }
            if(field.isFinal()){
                if(currentClazz != clazz || !currentMethod.getName().equals("<clinit>")){
                    throw new RuntimeException("IllegalAccessError");
                }
            }

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            Slots slots = clazz.getStaticVars();
            OperandStack stack = frame.getOperandStack();

            switch (descriptor){
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    slots.setInt(slotId, stack.popInt());
                    break;
                case "F":
                    slots.setFloat(slotId, stack.popFloat());
                    break;
                case "J":
                    slots.setLong(slotId, stack.popLong());
                    break;
                case "D":
                    slots.setDouble(slotId, stack.popDouble());
                    break;
                case "L":
                    slots.setRef(slotId, stack.popRef());
                    break;
            }
        }
    }

    public static class GET_STATIC extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            FieldRef fieldRef = cp.getConstant(index.value());
            RField field = fieldRef.resolveField();
            RClass clazz = field.getRClass();

            if(!field.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            Slots slots = clazz.getStaticVars();
            OperandStack stack = frame.getOperandStack();

            switch (descriptor){
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    stack.pushInt(slots.getInt(slotId));
                    break;
                case "F":
                    stack.pushFloat(slots.getFloat(slotId));
                    break;
                case "J":
                    stack.pushLong(slots.getLong(slotId));
                    break;
                case "D":
                    stack.pushDouble(slots.getDouble(slotId));
                    break;
                case "L":
                    stack.pushRef(slots.getRef(slotId));
                    break;
            }
        }
    }

}
