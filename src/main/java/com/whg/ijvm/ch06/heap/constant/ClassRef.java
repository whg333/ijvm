package com.whg.ijvm.ch06.heap.constant;

import com.whg.ijvm.ch06.classfile.constantinfo.member.ConstantClassInfo;
import com.whg.ijvm.ch06.heap.RConstantPool;

public class ClassRef extends SymRef{

    public ClassRef(RConstantPool cp, ConstantClassInfo classInfo){
        this.cp = cp;
        className = classInfo.getName();
    }

}
