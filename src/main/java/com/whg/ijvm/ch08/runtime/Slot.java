package com.whg.ijvm.ch08.runtime;

import com.whg.ijvm.ch08.heap.RObject;

public class Slot {

    int num;
    RObject ref;

    Slot(){

    }

    private Slot(int num, RObject ref){
        this.num = num;
        this.ref = ref;
    }

    public Slot copy(){
        return new Slot(num, ref);
    }

    @Override
    public String toString() {
        return "{" + num + " " + ref + "}";
    }
}
