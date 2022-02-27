package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.MemberInfo;
import com.whg.ijvm.ch06.classfile.uint.Uint16;

public abstract class RClassMember {

    Uint16 accessFlags;
    String name;
    String descriptor;
    RClass clazz;

    protected void copyMemberInfo(MemberInfo memberInfo){
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
    }

    abstract void copyAttributes(MemberInfo cfFiled);

    public boolean isFinal() {
        return AccessFlags.isFinal(accessFlags());
    }

    public boolean isStatic(){
        return AccessFlags.isStatic(accessFlags());
    }

    private int accessFlags(){
        return accessFlags.value();
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

    public String getDescriptor() {
        return descriptor;
    }

}
