package com.whg.ijvm.ch09.heap;

public class RObject implements Cloneable{

    protected RClass linkClass; // 此实例是类对象才会存在，关联到方法区中，对应的类
    protected RClass clazz; // 正常对象，对象到类的关联：Object -> Class，指明此实例是哪个类
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

    @Override
    public RObject clone() {
        Slots cloneData = ((Slots)data).clone();
        return new RObject(clazz, cloneData);
    }

    // reflection
    public void setRefVar(String name, String descriptor, RObject ref) {
        RField field = clazz.getField(name, descriptor, false);
        Slots fields = getFields();
        fields.setRef(field.slotId, ref);
    }
    // reflection
    public <T extends RObject> T getRefVar(String name, String descriptor) {
        RField field = clazz.getField(name, descriptor, false);
        Slots fields = getFields();
        return (T)fields.getRef(field.slotId);
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

    public RClass getExtra() {
        return linkClass;
    }
    public void setExtra(RClass extra) {
        this.linkClass = extra;
    }

}
