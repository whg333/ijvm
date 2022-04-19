package com.whg.ijvm.ch10.classfile.constantinfo.member;

import com.whg.ijvm.ch10.classfile.ClassReader;
import com.whg.ijvm.ch10.classfile.ConstantPool;
import com.whg.ijvm.ch10.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch10.classfile.uint.Uint16;

public class ClassInfo implements ConstantInfo {

    ConstantPool cp;
    Uint16 nameIndex;

    public ClassInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
    }

    public String getName(){
        return cp.getUtf8(nameIndex);
    }

}
