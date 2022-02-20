package com.whg.ijvm.ch05.runtime;

public class RFrame {

    RFrame lower;
    private LocalVars localVars;
    private OperandStack operandStack;

    RThread thread;
    int nextPc;

    RFrame(RThread thread, int maxLocals, int maxStack){
        this(maxLocals, maxStack);
        this.thread = thread;
    }

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

    public int getPc(){
        return thread.getPc();
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }
    public int getNextPc() {
        return nextPc;
    }
}
