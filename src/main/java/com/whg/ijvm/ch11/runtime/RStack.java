package com.whg.ijvm.ch11.runtime;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * 方法执行栈，内部由 方法执行栈帧 堆叠起来
 */
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

    public void clear(){
        while(!isEmpty()){
            pop();
        }
    }

    public List<RFrame> getFrames() {
        List<RFrame> frames = new ArrayList<>(size);
        for(RFrame frame=top;frame!=null;frame=frame.lower){
            frames.add(frame);
        }
        return frames;
    }
}
