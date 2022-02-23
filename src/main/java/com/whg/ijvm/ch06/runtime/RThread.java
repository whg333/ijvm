package com.whg.ijvm.ch06.runtime;

public class RThread {

    int pc;
    RStack stack;

    public RThread(){
        stack = new RStack(1024);
    }

    public RFrame newFrame(int maxLocals, int maxStack){
        return new RFrame(this, maxLocals, maxStack);
    }

    public void pushFrame(RFrame frame){
        stack.push(frame);
    }

    public RFrame popFrame(){
        return stack.pop();
    }

    public RFrame peekFrame(){
        return stack.peek();
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

}
