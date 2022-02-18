package com.whg.ijvm.ch03.classfile.constantinfo.member;

import com.whg.ijvm.ch03.Pair;
import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class NameAndTypeInfo implements ConstantInfo {

    ConstantPool cp;
    Uint16 nameIndex;
    Uint16 descriptorIndex;

    public NameAndTypeInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }

    public Pair<String, String> getNameAndType(){
        return Pair.of(cp.getUtf8(nameIndex), cp.getUtf8(descriptorIndex));
    }

}
