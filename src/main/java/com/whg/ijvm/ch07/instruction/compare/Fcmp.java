package com.whg.ijvm.ch07.instruction.compare;

import com.whg.ijvm.ch07.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch07.runtime.OperandStack;
import com.whg.ijvm.ch07.runtime.RFrame;

public class Fcmp {

    public static class FCMPG extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            fcmp(frame, true);
        }
    }
    public static class FCMPL extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            fcmp(frame, false);
        }
    }

    private static void fcmp(RFrame frame, boolean gFlag){
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
        if(v1 > v2){
            stack.pushInt(1);
        }else if(v1 == v2){
            stack.pushInt(0);
        }else if(v1 < v2){
            stack.pushInt(-1);
        }else{
            if(gFlag){
                stack.pushInt(1);
            }else{
                stack.pushInt(-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Float.isNaN(Float.NaN));
        System.out.println(Float.isNaN(Float.POSITIVE_INFINITY));

        float v2 = 1f;
        float v1 = Float.NaN;
        if(v1 > v2){
            System.out.println(1);
        }else if(v1 == v2){
            System.out.println(0);
        }else if(v1 < v2){
            System.out.println(-1);
        }else{
            System.out.println("float NaN");
        }
    }

}
