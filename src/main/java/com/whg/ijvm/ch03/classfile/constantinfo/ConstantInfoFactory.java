package com.whg.ijvm.ch03.classfile.constantinfo;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.constantinfo.member.FieldRefInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.member.InterfaceMethodRefInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.member.MemberRefInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.numeric.ConstantDoubleInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.numeric.ConstantFloatInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.numeric.ConstantIntegerInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.numeric.ConstantLongInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.string.ConstantStringInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.string.ConstantUtf8Info;
import com.whg.ijvm.ch03.classfile.uint.Uint8;

public class ConstantInfoFactory {

    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp){
        Uint8 tag = reader.readUint8();
        ConstantInfo c = newConstantInfo(tag, cp);
        c.readInfo(reader);
        return c;
    }

    private static ConstantInfo newConstantInfo(Uint8 tag, ConstantPool cp){
        ConstantTag constantTag = ConstantTag.valueOf(tag.value());
        switch (constantTag) {
            case Clazz:
                return new ConstantClassInfo(cp);
            case FieldRef:
                return new FieldRefInfo(cp);
            case MethodRef:
                return new MemberRefInfo(cp);
            case InterfaceMethodRef:
                return new InterfaceMethodRefInfo(cp);
            case String:
                return new ConstantStringInfo(cp);
            case Integer:
                return new ConstantIntegerInfo();
            case Float:
                return new ConstantFloatInfo();
            case Long:
                return new ConstantLongInfo();
            case Double:
                return new ConstantDoubleInfo();
            case NameAndType:
                return new NameAndTypeInfo();
            case Utf8:
                return new ConstantUtf8Info();
            case MethodHandle:
            case MethodType:
            case InvokeDynamic:
            default:
                throw new IllegalArgumentException("Unsupported tag="+constantTag);
        }
    }

}
