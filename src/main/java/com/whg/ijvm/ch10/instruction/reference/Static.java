package com.whg.ijvm.ch10.instruction.reference;

import com.whg.ijvm.ch10.heap.*;
import com.whg.ijvm.ch10.heap.constant.FieldRef;
import com.whg.ijvm.ch10.instruction.base.Index16Instruction;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;

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
            if(!clazz.isInit()){
                frame.revertNextPc();
                clazz.init(frame.getThread());
                return;
            }

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

            char firstDesc = descriptor.charAt(0);
            switch (firstDesc){
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    slots.setInt(slotId, stack.popInt());
                    break;
                case 'F':
                    slots.setFloat(slotId, stack.popFloat());
                    break;
                case 'J':
                    slots.setLong(slotId, stack.popLong());
                    break;
                case 'D':
                    slots.setDouble(slotId, stack.popDouble());
                    break;
                case 'L':
                case '[':
                    slots.setRef(slotId, stack.popRef());
                    break;
                default:
                    if(isRefDesc(descriptor)){
                        slots.setRef(slotId, stack.popRef());
                    }
                    break;
            }
        }
    }

    // 引用类型的描述符：L+类的完全限定名+分号
    private static boolean isRefDesc(String descriptor){
        return descriptor.startsWith("L") && descriptor.endsWith(";");
    }

    public static class GET_STATIC extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            FieldRef fieldRef = cp.getConstant(index.value());
            RField field = fieldRef.resolveField();
            RClass clazz = field.getRClass();
            if(!clazz.isInit()){
                frame.revertNextPc();
                clazz.init(frame.getThread());
                return;
            }

            if(!field.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            Slots slots = clazz.getStaticVars();
            OperandStack stack = frame.getOperandStack();

            char firstDesc = descriptor.charAt(0);
            switch (firstDesc){
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    stack.pushInt(slots.getInt(slotId));
                    break;
                case 'F':
                    stack.pushFloat(slots.getFloat(slotId));
                    break;
                case 'J':
                    stack.pushLong(slots.getLong(slotId));
                    break;
                case 'D':
                    stack.pushDouble(slots.getDouble(slotId));
                    break;
                case 'L':
                case '[':
                    stack.pushRef(slots.getRef(slotId));
                    break;
                default:
                    if(isRefDesc(descriptor)){
                        stack.pushRef(slots.getRef(slotId));
                    }
                    break;
            }
        }
    }

}
