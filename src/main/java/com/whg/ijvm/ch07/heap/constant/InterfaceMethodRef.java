package com.whg.ijvm.ch07.heap.constant;

import com.whg.ijvm.ch07.classfile.constantinfo.member.InterfaceMethodRefInfo;
import com.whg.ijvm.ch07.heap.RConstantPool;
import com.whg.ijvm.ch07.heap.RMethod;

public class InterfaceMethodRef extends MemberRef{
    
    RMethod method;

    public InterfaceMethodRef(RConstantPool cp, InterfaceMethodRefInfo refInfo) {
        super(cp, refInfo);
    }

}
