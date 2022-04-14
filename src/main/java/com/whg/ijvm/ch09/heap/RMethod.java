package com.whg.ijvm.ch09.heap;

import com.whg.ijvm.ch09.classfile.ClassFile;
import com.whg.ijvm.ch09.classfile.MemberInfo;
import com.whg.ijvm.ch09.classfile.attribute.impl.CodeAttribute;

public class RMethod extends RClassMember{

    int maxStack;
    int maxLocals;
    byte[] code;

    int argSlotCount;

    RMethod(RClass clazz, MemberInfo cfMethod){
        this.clazz = clazz;
        copyMemberInfo(cfMethod);
        copyAttributes(cfMethod);
        calcArgSlotCount();
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
        RMethodDescriptor md = RMethodDescriptor.parse(descriptor);
        for(String paramType: md.getParameterTypes()){
            argSlotCount++;
            if(paramType.equals("J") || paramType.equals("D")){
                argSlotCount++;
            }
        }
        if(!isStatic()){
            argSlotCount++;
        }
        if(isNative()){
            injectCodeAttribute(md.returnType);
        }
    }

    private void injectCodeAttribute(String returnType) {
        maxStack = 4;
        maxLocals = argSlotCount;
        char firstChar = returnType.charAt(0);
        switch (firstChar){
            case 'V': // return
                code = new byte[]{(byte) 0xfe, (byte) 0xb1};
                break;
            case 'D': // dreturn
                code = new byte[]{(byte) 0xfe, (byte) 0xaf};
                break;
            case 'F': // freturn
                code = new byte[]{(byte) 0xfe, (byte) 0xae};
                break;
            case 'J': // lreturn
                code = new byte[]{(byte) 0xfe, (byte) 0xad};
                break;
            case 'L': // areturn
                code = new byte[]{(byte) 0xfe, (byte) 0xb0};
                break;
            default: // ireturn
                code = new byte[]{(byte) 0xfe, (byte) 0xac};
                break;
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

    @Override
    public String toString() {
        String className = getRClass().getSimpleName();
        return className + '.' +
                getName() +
                getDescriptor();
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

    public int getArgSlotCount() {
        return argSlotCount;
    }
}
