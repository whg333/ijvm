package com.whg.ijvm.ch06.classfile.attribute;

import com.whg.ijvm.ch06.classfile.ConstantPool;
import com.whg.ijvm.ch06.classfile.attribute.impl.*;

public class AttributeInfoFactory {

    public static AttributeInfo newAttributeInfo(String name, int length, ConstantPool cp){
        switch (name){
            case "Code":
                return new CodeAttribute(name, length, cp);
            case "ConstantValue":
                return new ConstantValueAttribute(name, length, cp);
            case "Deprecated":
                return new DeprecatedAttribute(name, length, cp);
            case "Exceptions":
                return new ExceptionsAttribute(name, length, cp);
            case "LineNumberTable":
                return new LineNumberTableAttribute(name, length, cp);
            case "LocalVariableTable":
                return new LocalVariableTableAttribute(name, length, cp);
            case "SourceFile":
                return new SourceFileAttribute(name, length, cp);
            case "Synthetic":
                return new SyntheticAttribute(name, length, cp);
            default:
                return new UnParseAttribute(name, length, cp);
        }
    }

}
