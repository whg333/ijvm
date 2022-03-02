package com.whg.ijvm.ch07.runtime;

import com.whg.ijvm.ch07.heap.RMethod;

public class RThread {

    int pc;
    RStack stack;

    public RThread(){
        stack = new RStack(1024);
    }

    public RFrame newFrame(RMethod method){
        return new RFrame(this, method);
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
