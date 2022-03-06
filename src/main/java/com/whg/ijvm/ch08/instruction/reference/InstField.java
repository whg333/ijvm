package com.whg.ijvm.ch08.instruction.reference;

import com.whg.ijvm.ch08.heap.*;
import com.whg.ijvm.ch08.heap.constant.FieldRef;
import com.whg.ijvm.ch08.instruction.base.Index16Instruction;
import com.whg.ijvm.ch08.runtime.OperandStack;
import com.whg.ijvm.ch08.runtime.RFrame;

import java.util.Objects;

public class InstField {

    public static class PUT_FIELD extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RMethod currentMethod = frame.getMethod();
            RClass currentClazz = currentMethod.getRClass();
            RConstantPool cp = currentClazz.getRConstantPool();
            FieldRef fieldRef = cp.getConstant(index.value());
            RField field = fieldRef.resolveField();
            RClass clazz = field.getRClass();

            if(field.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }
            if(field.isFinal()){
                if(currentClazz != clazz || !currentMethod.getName().equals("<init>")){
                    throw new RuntimeException("IllegalAccessError");
                }
            }

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            OperandStack stack = frame.getOperandStack();

            switch (descriptor){
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    int iVal = stack.popInt();
                    RObject iRef = stack.popRef();
                    Objects.requireNonNull(iRef);
                    iRef.getFields().setInt(slotId, iVal);
                    break;
                case "F":
                    float fVal = stack.popFloat();
                    RObject fRef = stack.popRef();
                    Objects.requireNonNull(fRef);
                    fRef.getFields().setFloat(slotId, fVal);
                    break;
                case "J":
                    long lVal = stack.popInt();
                    RObject lRef = stack.popRef();
                    Objects.requireNonNull(lRef);
                    lRef.getFields().setLong(slotId, lVal);
                    break;
                case "D":
                    double dVal = stack.popInt();
                    RObject dRef = stack.popRef();
                    Objects.requireNonNull(dRef);
                    dRef.getFields().setDouble(slotId, dVal);
                    break;
                case "L":
                    RObject val = stack.popRef();
                    RObject ref = stack.popRef();
                    Objects.requireNonNull(ref);
                    ref.getFields().setRef(slotId, val);
                    break;
            }
        }
    }

    public static class GET_FIELD extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            FieldRef fieldRef = cp.getConstant(index.value());
            RField field = fieldRef.resolveField();

            if(field.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }

            OperandStack stack = frame.getOperandStack();
            RObject ref = stack.popRef();
            Objects.requireNonNull(ref);

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            Slots slots = ref.getFields();

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
