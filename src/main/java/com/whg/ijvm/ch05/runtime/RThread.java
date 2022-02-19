package com.whg.ijvm.ch05.runtime;

public class RThread {

    int pc;
    RStack stack;

    public RThread(){
        stack = new RStack(1024);
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
