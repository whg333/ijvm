package com.whg.ijvm.ch11.instruction.compare;

import com.whg.ijvm.ch11.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch11.runtime.OperandStack;
import com.whg.ijvm.ch11.runtime.RFrame;

public class Dcmp {

    public static class DCMPG extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            dcmp(frame, true);
        }
    }
    public static class DCMPL extends NoOperandsInstruction{
        @Override
        public void execute(RFrame frame) {
            dcmp(frame, false);
        }
    }

    private static void dcmp(RFrame frame, boolean gFlag){
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
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
        System.out.println(Double.isNaN(Double.NaN));
        System.out.println(Double.isNaN(Double.POSITIVE_INFINITY));

        double v2 = 1f;
        double v1 = Double.NaN;
        if(v1 > v2){
            System.out.println(1);
        }else if(v1 == v2){
            System.out.println(0);
        }else if(v1 < v2){
            System.out.println(-1);
        }else{
            System.out.println("double NaN");
        }
    }

}
