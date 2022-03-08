package com.whg.ijvm.ch08.instruction.reference;

import com.whg.ijvm.ch08.classfile.uint.Uint8;
import com.whg.ijvm.ch08.heap.*;
import com.whg.ijvm.ch08.heap.constant.ClassRef;
import com.whg.ijvm.ch08.instruction.Instruction;
import com.whg.ijvm.ch08.instruction.base.BytecodeReader;
import com.whg.ijvm.ch08.instruction.base.Index16Instruction;
import com.whg.ijvm.ch08.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch08.runtime.OperandStack;
import com.whg.ijvm.ch08.runtime.RFrame;

public class Arr {

    private static final short AT_BOOLEAN =     4;
    private static final short AT_CHAR =        5;
    private static final short AT_FLOAT =       6;
    private static final short AT_DOUBLE =      7;
    private static final short AT_BYTE =        8;
    private static final short AT_SHORT =       9;
    private static final short AT_INT =         10;
    private static final short AT_LONG =        11;

    public static class NEW_ARRAY implements Instruction{

        Uint8 atype;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            atype = reader.readUint8();
        }

        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int count = stack.popInt();
            if(count < 0){
                throw new IllegalArgumentException("NegativeArraySizeException");
            }

            RClassLoader classLoader = frame.getMethod().getRClass().loader;
            RClass arrClass = getPrimitiveArrayClass(classLoader, atype.value());
            RArray array = arrClass.newArray(count);
            stack.pushRef(array);
        }

        private RClass getPrimitiveArrayClass(RClassLoader classLoader, short atype) {
            switch(atype){
                case AT_BOOLEAN:
                    return classLoader.loadClass("[Z");
                case AT_BYTE:
                    return classLoader.loadClass("[B");
                case AT_CHAR:
                    return classLoader.loadClass("[C");
                case AT_SHORT:
                    return classLoader.loadClass("[S");
                case AT_INT:
                    return classLoader.loadClass("[I");
                case AT_LONG:
                    return classLoader.loadClass("[J");
                case AT_FLOAT:
                    return classLoader.loadClass("[F");
                case AT_DOUBLE:
                    return classLoader.loadClass("[D");
                default:
                    throw new IllegalArgumentException("Invalid atype!");
            }
        }

    }

    public static class ANEW_ARRAY extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            ClassRef classRef = cp.getConstant(index.value());
            RClass componentClass = classRef.resolveClass();

            OperandStack stack = frame.getOperandStack();
            int count = stack.popInt();
            if(count < 0){
                throw new IllegalArgumentException("NegativeArraySizeException");
            }

            RClass arrClass = componentClass.getArrayClass();
            RArray array = arrClass.newArray(count);
            stack.pushRef(array);
        }
    }

    public static class ARRAY_LENGTH extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            OperandStack stack = frame.getOperandStack();
            RArray arrRef = stack.popArrRef();
            if(arrRef == null){
                throw new RuntimeException("NullPointerException");
            }

            int arrLen = arrRef.getArrayLength();
            stack.pushInt(arrLen);
        }
    }

}
