package com.whg.ijvm.ch07.instruction.reference;

import com.whg.ijvm.ch07.heap.RClass;
import com.whg.ijvm.ch07.heap.RConstantPool;
import com.whg.ijvm.ch07.heap.RMethod;
import com.whg.ijvm.ch07.heap.RObject;
import com.whg.ijvm.ch07.heap.constant.MethodRef;
import com.whg.ijvm.ch07.instruction.base.Index16Instruction;
import com.whg.ijvm.ch07.instruction.base.MethodInvokeLogic;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Invoke {

    public static class INVOKE_STATIC extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            MethodRef methodRef = cp.getConstant(index.value());
            RMethod method = methodRef.resolveMethod();
            if(!method.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError");
            }
            MethodInvokeLogic.invokeMethod(frame, method);
        }
    }

    public static class INVOKE_SPECIAL extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RClass currentClass = frame.getMethod().getRClass();
            RConstantPool cp = currentClass.getRConstantPool();
            MethodRef methodRef = cp.getConstant(index.value());
            RClass clazz = methodRef.resolveClass();
            RMethod method = methodRef.resolveMethod();

            if(method.getName().equals("<init>") && method.getRClass() != clazz){
                throw new RuntimeException("NoSuchMethodError");
            }
            if(method.isStatic()){
                throw new RuntimeException("IncompatibleClassChangeError)";
            }

            RObject ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount());
            if(ref == null){
                throw new RuntimeException("NullPointerException");
            }

            frame.getOperandStack().popRef();
        }
    }

    public static class INVOKE_VIRTUAL extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
            RConstantPool cp = frame.getMethod().getRClass().getRConstantPool();
            MethodRef methodRef = cp.getConstant(index.value());
            if(methodRef.getName().equals("println")){
                OperandStack stack = frame.getOperandStack();
                String descriptor = methodRef.getDescriptor();
                switch (descriptor){
                    case "(Z)V":
                        printf(String.format("%b\n", stack.popInt() != 0));
                        break;
                    case "(C)V":
                        printf(String.format("%c\n", stack.popInt()));
                        break;
                    case "(B)V":
                        printf(String.format("%d\n", stack.popInt()));
                        break;
                    case "(S)V":
                        printf(String.format("%d\n", stack.popInt()));
                        break;
                    case "(I)V":
                        printf(String.format("%d\n", stack.popInt()));
                        break;
                    case "(J)V":
                        printf(String.format("%d\n", stack.popLong()));
                        break;
                    case "(F)V":
                        printf(String.format("%f\n", stack.popFloat()));
                        break;
                    case "(D)V":
                        printf(String.format("%f\n", stack.popDouble()));
                        break;
                    default:
                        throw new RuntimeException("println: "+descriptor);
                }
                // TODO
                // stack.popRef();
            }
        }
    }

    private static void printf(String s){
        System.out.print(s);
    }

}
