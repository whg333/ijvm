package com.whg.ijvm.ch06.heap.constant;

import com.whg.ijvm.ch06.classfile.constantinfo.member.ClassInfo;
import com.whg.ijvm.ch06.heap.RConstantPool;

public class ClassRef extends SymRef{

    public ClassRef(RConstantPool cp, ClassInfo classInfo){
        this.cp = cp;
        className = classInfo.getName();
    }

}
