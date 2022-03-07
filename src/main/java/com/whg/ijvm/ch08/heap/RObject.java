package com.whg.ijvm.ch08.heap;

public class RObject {

    protected RClass clazz;
    protected Object data;

    RObject(){

    }

    RObject(RClass clazz){
        this.clazz = clazz;
        this.data = new Slots(clazz.getInstanceSlotCount());
    }

    public Slots getFields() {
        return (Slots)data;
    }

    public boolean isInstanceOf(RClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    public RClass getRClass() {
        return clazz;
    }
}
