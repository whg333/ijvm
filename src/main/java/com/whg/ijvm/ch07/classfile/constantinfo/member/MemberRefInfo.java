package com.whg.ijvm.ch07.classfile.constantinfo.member;

import com.whg.ijvm.ch07.classfile.ClassReader;
import com.whg.ijvm.ch07.classfile.ConstantPool;
import com.whg.ijvm.ch07.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch07.classfile.uint.Uint16;
import org.apache.commons.lang3.tuple.Pair;

public abstract class MemberRefInfo implements ConstantInfo {

    protected ConstantPool cp;
    protected Uint16 classIndex;
    protected Uint16 nameAndTypeIndex;

    public MemberRefInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName(){
        return cp.getClassName(classIndex);
    }

    public Pair<String, String> getNameAndDescriptor(){
        return cp.getNameAndType(nameAndTypeIndex);
    }

}
