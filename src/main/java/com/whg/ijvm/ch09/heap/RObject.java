package com.whg.ijvm.ch09.heap;

public class RObject {

    protected RClass clazz;
    protected Object data;

    // for array
    RObject(){

    }

    RObject(RClass clazz){
        this(clazz, new Slots(clazz.getInstanceSlotCount()));
    }

    RObject(RClass clazz, Object data){
        this.clazz = clazz;
        this.data = data;
    }

    // reflection
    public void setRefVar(String name, String descriptor, RObject ref) {
        RField field = clazz.getField(name, descriptor, false);
        Slots slots = (Slots)data;
        slots.setRef(field.slotId, ref);
    }
    public <T extends RObject> T getRefVar(String name, String descriptor) {
        RField field = clazz.getField(name, descriptor, false);
        Slots slots = (Slots)data;
        return (T)slots.getRef(field.slotId);
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
