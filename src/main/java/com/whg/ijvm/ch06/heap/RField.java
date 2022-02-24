package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ClassFile;
import com.whg.ijvm.ch06.classfile.MemberInfo;

public class RField extends RClassMember{

    RField(RClass clazz, MemberInfo cfFiled){
        this.clazz = clazz;
        copyMemberInfo(cfFiled);
    }

    public static RField[] newFields(RClass clazz, ClassFile cf) {
        MemberInfo[] cfFields = cf.getFields();
        RField[] fields = new RField[cfFields.length];
        for(int i=0;i<cfFields.length;i++){
            fields[i] = new RField(clazz, cfFields[i]);
        }
        return fields;
    }
}
