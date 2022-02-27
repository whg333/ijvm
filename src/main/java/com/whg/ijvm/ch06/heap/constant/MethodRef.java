package com.whg.ijvm.ch06.heap.constant;

import com.whg.ijvm.ch06.classfile.constantinfo.member.MethodRefInfo;
import com.whg.ijvm.ch06.heap.RConstantPool;
import com.whg.ijvm.ch06.heap.RMethod;

public class MethodRef extends MemberRef{

    RMethod method;

    MethodRef(RConstantPool cp, MethodRefInfo refInfo) {
        super(cp, refInfo);
    }
}
