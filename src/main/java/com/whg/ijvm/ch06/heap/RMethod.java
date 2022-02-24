package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ClassFile;
import com.whg.ijvm.ch06.classfile.MemberInfo;
import com.whg.ijvm.ch06.classfile.attribute.impl.CodeAttribute;

public class RMethod extends RClassMember{

    int maxStack;
    int maxLocals;
    byte[] code;

    RMethod(RClass clazz, MemberInfo cfMethod){
        this.clazz = clazz;
        copyMemberInfo(cfMethod);
    }

    public RMethod[] newMethods(RClass clazz, ClassFile cf){
        MemberInfo[] cfMethods = cf.getMethods();
        RMethod[] methods = new RMethod[cfMethods.length];
        for(int i=0;i<cfMethods.length;i++){
            methods[i] = new RMethod(clazz, cfMethods[i]);
        }
        return methods;
    }

    void copyAttribute(MemberInfo cfMethod){
        CodeAttribute codeAttr = cfMethod.getCodeAttribute();
        maxLocals = codeAttr.getMaxLocals().value();
        maxStack = codeAttr.getMaxStack().value();
        code = codeAttr.getCode();
    }

}
