package com.whg.ijvm.ch06.heap;

public class RObject {

    RClass clazz;
    Slots fields;

    RObject(RClass clazz){
        this.clazz = clazz;
        this.fields = new Slots(clazz.getInstanceSlotCount());
    }

    public Slots getFields() {
        return fields;
    }

    public boolean isInstanceOf(RClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

}
