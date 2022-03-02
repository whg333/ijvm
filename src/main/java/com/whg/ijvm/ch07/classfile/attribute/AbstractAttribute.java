package com.whg.ijvm.ch07.classfile.attribute;

import com.whg.ijvm.ch07.classfile.ConstantPool;

public abstract class AbstractAttribute implements AttributeInfo{

    protected String name;
    protected int length;
    protected ConstantPool cp;

    public AbstractAttribute(String name, int length, ConstantPool cp){
        this.name = name;
        this.length = length;
        this.cp = cp;
    }

    public String getName(){
        return name;
    }

}
