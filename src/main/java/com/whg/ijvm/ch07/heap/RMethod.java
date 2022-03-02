package com.whg.ijvm.ch07.heap;

import com.whg.ijvm.ch07.classfile.ClassFile;
import com.whg.ijvm.ch07.classfile.MemberInfo;
import com.whg.ijvm.ch07.classfile.attribute.impl.CodeAttribute;

public class RMethod extends RClassMember{

    int maxStack;
    int maxLocals;
    byte[] code;

    short argSlotCount;

    RMethod(RClass clazz, MemberInfo cfMethod){
        this.clazz = clazz;
        copyMemberInfo(cfMethod);
        copyAttributes(cfMethod);
        // calcArgSlotCount();
    }

    @Override
    void copyAttributes(MemberInfo cfMethod){
        CodeAttribute codeAttr = cfMethod.getCodeAttribute();
        if(codeAttr != null){
            maxLocals = codeAttr.getMaxLocals().value();
            maxStack = codeAttr.getMaxStack().value();
            code = codeAttr.getCode();
        }
    }

    void calcArgSlotCount(){
        RMethodDescriptor methodDescriptor = RMethodDescriptor.parse(descriptor);
        for(String paramType: methodDescriptor.getParameterTypes()){
            argSlotCount++;
            if(paramType.equals("J") || paramType.equals("D")){
                argSlotCount++;
            }
        }
        if(!isStatic()){
            argSlotCount++;
        }
    }

    public static RMethod[] newMethods(RClass clazz, ClassFile cf){
        MemberInfo[] cfMethods = cf.getMethods();
        RMethod[] methods = new RMethod[cfMethods.length];
        for(int i=0;i<cfMethods.length;i++){
            methods[i] = new RMethod(clazz, cfMethods[i]);
        }
        return methods;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public short getArgSlotCount() {
        return argSlotCount;
    }
}
