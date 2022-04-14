package com.whg.ijvm.ch09.heap.constant;

import com.whg.ijvm.ch09.classfile.constantinfo.member.MemberRefInfo;
import com.whg.ijvm.ch09.heap.RConstantPool;
import org.apache.commons.lang3.tuple.Pair;

public class MemberRef extends SymRef{

    protected String name;
    protected String descriptor;

    MemberRef(RConstantPool cp, MemberRefInfo refInfo){
        this.cp = cp;
        copyMemberRefInfo(refInfo);
    }

    void copyMemberRefInfo(MemberRefInfo refInfo){
        className = refInfo.getClassName();

        Pair<String, String> nameAndDescriptor = refInfo.getNameAndDescriptor();
        name = nameAndDescriptor.getLeft();
        descriptor = nameAndDescriptor.getRight();
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

}
