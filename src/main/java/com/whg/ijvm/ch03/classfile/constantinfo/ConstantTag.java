package com.whg.ijvm.ch03.classfile.constantinfo;

public enum ConstantTag {

    Clazz(7),
    FieldRef(9),
    MethodRef(10),
    InterfaceMethodRef(11),
    String(8),
    Integer(3),
    Float(4),
    Long(5),
    Double(6),
    NameAndType(12),
    Utf8(1),
    MethodHandle(15),
    MethodType(16),
    InvokeDynamic(18),
    ;

    public short code;
    ConstantTag(int code){
        this((short)code);
    }
    ConstantTag(short code){
        this.code = code;
    }

    public static ConstantTag valueOf(short code){
        for(ConstantTag tag: values()){
            if(tag.code == code){
                return tag;
            }
        }
        throw new IllegalArgumentException("ConstantTag code="+code);
    }

}
