package com.whg.ijvm.ch03.classfile.constantinfo;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.uint.Uint8;

public interface ConstantInfo {
    void readInfo(ClassReader reader);

    default ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp){
        Uint8 tag = reader.readUint8();
        ConstantInfo c = newConstantInfo(tag, cp);
        c.readInfo(reader);
        return c;
    }
    default ConstantInfo newConstantInfo(Uint8 tag, ConstantPool cp){
        ConstantTag constantTag = ConstantTag.valueOf(tag.value());
        switch (constantTag) {
            case Clazz:
                break;
            case FieldRef:
                break;
            case MethodRef:
                break;
            case InterfaceMethodRef:
                break;
            case String:
                break;
            case Integer:
                break;
            case Float:
                break;
            case Long:
                break;
            case Double:
                break;
            case NameAndType:
                break;
            case Utf8:
                break;
            case MethodHandle:
                break;
            case MethodType:
                break;
            case InvokeDynamic:
                break;
        }
        return null;
    }
}
