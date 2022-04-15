package com.whg.ijvm.ch09.instruction.reference;

import com.whg.ijvm.ch09.classfile.uint.Uint16;
import com.whg.ijvm.ch09.classfile.uint.Uint8;
import com.whg.ijvm.ch09.heap.*;
import com.whg.ijvm.ch09.heap.constant.ClassRef;
import com.whg.ijvm.ch09.instruction.Instruction;
import com.whg.ijvm.ch09.instruction.base.BytecodeReader;
import com.whg.ijvm.ch09.instruction.base.Index16Instruction;
import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.runtime.OperandStack;
import com.whg.ijvm.ch09.runtime.RFrame;

import java.util.Arrays;

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
                throw new NegativeArraySizeException();
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

        @Override
        public String toString() {
            return Instruction.string(this)+" "+atype.value();
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
                throw new NegativeArraySizeException();
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

    public static class MULTI_ANEW_ARRAY implements Instruction{

        Uint16 index;
        Uint8 dimensions;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readUint16();
            dimensions = reader.readUint8();
        }

        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            ClassRef classRef = cp.getConstant(index.value());
            RClass arrClass = classRef.resolveClass();

            OperandStack stack = frame.getOperandStack();
            int[] counts = popAndCheckCounts(stack, dimensions.value());
            RArray array = newMultiDimensionalArray(counts, arrClass);
            stack.pushRef(array);
        }

        private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
            int[] counts = new int[dimensions];
            for(int i=dimensions-1;i>=0;i--){
                counts[i] = stack.popInt();
                if(counts[i] < 0){
                    throw new NegativeArraySizeException();
                }
            }
            return counts;
        }

        private RArray newMultiDimensionalArray(int[] counts, RClass arrClass) {
            int count = counts[0];
            RArray array = arrClass.newArray(count);
            if(counts.length > 1){
                RObject[] refs = array.getRefs();
                for(int i=0;i< refs.length;i++){
                    refs[i] = newMultiDimensionalArray(
                            Arrays.copyOfRange(counts, 1, counts.length),
                            arrClass.getComponentClass());
                }
            }
            return array;
        }

    }

}
