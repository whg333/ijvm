package com.whg.ijvm.ch10.runtime;

import com.whg.ijvm.ch10.heap.RMethod;

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

    public RFrame currentFrame(){
        return topFrame();
    }
    public RFrame topFrame(){
        return stack.top();
    }

    public boolean isStackEmpty() {
        return stack.isEmpty();
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

}
