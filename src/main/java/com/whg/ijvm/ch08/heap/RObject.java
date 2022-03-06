package com.whg.ijvm.ch08.heap;

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

    public RClass getRClass() {
        return clazz;
    }
}
