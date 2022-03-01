package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ClassFile;
import com.whg.ijvm.ch06.classfile.MemberInfo;
import com.whg.ijvm.ch06.classfile.attribute.impl.ConstantValueAttribute;

public class RField extends RClassMember{

    int constValueIndex;
    int slotId;

    RField(RClass clazz, MemberInfo cfFiled){
        this.clazz = clazz;
        copyMemberInfo(cfFiled);
        copyAttributes(cfFiled);
    }

    @Override
    void copyAttributes(MemberInfo cfFiled) {
        ConstantValueAttribute valAttr = cfFiled.getConstantValueAttribute();
        if(valAttr != null){
            constValueIndex = valAttr.getConstantValueIndex().value();
        }
    }

    public static RField[] newFields(RClass clazz, ClassFile cf) {
        MemberInfo[] cfFields = cf.getFields();
        RField[] fields = new RField[cfFields.length];
        for(int i=0;i<cfFields.length;i++){
            fields[i] = new RField(clazz, cfFields[i]);
        }
        return fields;
    }

    public int getSlotId() {
        return slotId;
    }
}
