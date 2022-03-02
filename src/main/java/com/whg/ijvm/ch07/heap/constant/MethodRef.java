package com.whg.ijvm.ch07.heap.constant;

import com.whg.ijvm.ch07.classfile.constantinfo.member.MethodRefInfo;
import com.whg.ijvm.ch07.heap.RConstantPool;
import com.whg.ijvm.ch07.heap.RMethod;

public class MethodRef extends MemberRef{

    RMethod method;

    public MethodRef(RConstantPool cp, MethodRefInfo refInfo) {
        super(cp, refInfo);
    }
}
