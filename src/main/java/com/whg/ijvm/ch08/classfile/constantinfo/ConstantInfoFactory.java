package com.whg.ijvm.ch08.classfile.constantinfo;

import com.whg.ijvm.ch08.classfile.ClassReader;
import com.whg.ijvm.ch08.classfile.ConstantPool;
import com.whg.ijvm.ch08.classfile.constantinfo.member.*;
import com.whg.ijvm.ch08.classfile.constantinfo.method.InvokeDynamicInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.method.MethodHandleInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.method.MethodTypeInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.numeric.ConstantDoubleInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.numeric.ConstantFloatInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.numeric.ConstantIntegerInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.numeric.ConstantLongInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.string.ConstantStringInfo;
import com.whg.ijvm.ch08.classfile.constantinfo.string.ConstantUtf8Info;
import com.whg.ijvm.ch08.classfile.uint.Uint8;

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
                return new ClassInfo(cp);
            case NameAndType:
                return new NameAndTypeInfo(cp);
            case FieldRef:
                return new FieldRefInfo(cp);
            case MethodRef:
                return new MethodRefInfo(cp);
            case InterfaceMethodRef:
                return new InterfaceMethodRefInfo(cp);

            case String:
                return new ConstantStringInfo(cp);
            case Utf8:
                return new ConstantUtf8Info();

            case Integer:
                return new ConstantIntegerInfo();
            case Long:
                return new ConstantLongInfo();
            case Float:
                return new ConstantFloatInfo();
            case Double:
                return new ConstantDoubleInfo();

            case MethodHandle:
                return new MethodHandleInfo();
            case MethodType:
                return new MethodTypeInfo();
            case InvokeDynamic:
                return new InvokeDynamicInfo();
                
            default:
                throw new IllegalArgumentException("Unsupported tag="+constantTag);
        }
    }

}
