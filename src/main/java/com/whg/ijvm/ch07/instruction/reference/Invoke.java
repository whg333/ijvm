package com.whg.ijvm.ch07.instruction.reference;

import com.whg.ijvm.ch07.heap.RConstantPool;
import com.whg.ijvm.ch07.heap.constant.MethodRef;
import com.whg.ijvm.ch07.instruction.base.Index16Instruction;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Invoke {

    public static class INVOKE_SPECIAL extends Index16Instruction{
        @Override
        public void execute(RFrame frame) {
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
