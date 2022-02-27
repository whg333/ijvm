package com.whg.ijvm.ch06.heap.constant;

import com.whg.ijvm.ch06.classfile.constantinfo.member.FieldRefInfo;
import com.whg.ijvm.ch06.heap.RConstantPool;
import com.whg.ijvm.ch06.heap.RField;

public class FieldRef extends MemberRef{

    RField field;

    FieldRef(RConstantPool cp, FieldRefInfo refInfo) {
        super(cp, refInfo);
    }
}
