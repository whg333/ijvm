package com.whg.ijvm.ch09.runtime;

import com.whg.ijvm.ch09.heap.RObject;

public class Slot implements Cloneable{

    int num;
    RObject ref;

    Slot(){

    }

    private Slot(int num, RObject ref){
        this.num = num;
        this.ref = ref;
    }

    @Override
    public Slot clone() {
        return new Slot(num, ref);
    }

    @Override
    public String toString() {
        return "{" + num + " " + ref + "}";
    }
}
