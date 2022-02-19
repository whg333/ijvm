package com.whg.ijvm.ch04.runtime;

public class RFrame {

    RFrame lower;
    private LocalVars localVars;
    private OperandStack operandStack;

    public RFrame(int maxLocals, int maxStack){
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

}
