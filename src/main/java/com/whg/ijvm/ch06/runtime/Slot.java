package com.whg.ijvm.ch06.runtime;

import com.whg.ijvm.ch06.heap.RObject;

public class Slot {

    int num;
    RObject ref;

    @Override
    public String toString() {
        return "{" + num + " " + ref + "}";
    }
}
