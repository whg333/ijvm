package com.whg.ijvm.ch10.heap;

import com.whg.ijvm.ch10.runtime.LocalVars;

public class Slots extends LocalVars implements Cloneable{

    public Slots(int length) {
        super(length);
    }

    @Override
    public Slots clone() {
        int length = slots.length;
        Slots clone = new Slots(length);
        // 不能使用System.arraycopy拷贝对象数组，因为是浅拷贝，即拷贝对象的地址而不是值
        // 所以使用下面的深拷贝，Slot.clone创造全新的值对象
        for(int i=0;i<length;i++){
            clone.slots[i] = slots[i].clone();
        }
        return clone;
    }

}
