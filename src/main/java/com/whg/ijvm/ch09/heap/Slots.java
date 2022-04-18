package com.whg.ijvm.ch09.heap;

import com.whg.ijvm.ch09.runtime.LocalVars;

public class Slots extends LocalVars implements Cloneable{

    public Slots(int length) {
        super(length);
    }

    @Override
    public Slots clone() {
        int length = slots.length;
        Slots clone = new Slots(length);
        System.arraycopy(slots, 0, clone.slots, 0, length);
        return clone;
    }

}
