package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.MemberInfo;
import com.whg.ijvm.ch06.classfile.uint.Uint16;

public class RClassMember {

    Uint16 accessFlags;
    String name;
    String descriptor;
    RClass clazz;

    protected void copyMemberInfo(MemberInfo memberInfo){
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
    }

}
