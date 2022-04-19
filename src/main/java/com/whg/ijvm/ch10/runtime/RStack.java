package com.whg.ijvm.ch10.runtime;

import java.util.EmptyStackException;

public class RStack {

    int capacity;
    int size;
    RFrame top;

    public RStack(int capacity){
        this.capacity = capacity;
    }

    public void push(RFrame frame){
        if(size >= capacity){
            throw new StackOverflowError();
        }
        if(top != null){
            frame.lower = top;
        }
        top = frame;
        frame.level = size;
        size++;
    }

    public RFrame pop(){
        checkNotEmpty();
        RFrame currTop = top;
        top = currTop.lower;
        currTop.lower = null;
        size--;
        return currTop;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public RFrame top(){
        checkNotEmpty();
        return top;
    }

    private void checkNotEmpty(){
        if(top == null){
            throw new EmptyStackException();
        }
    }

}
