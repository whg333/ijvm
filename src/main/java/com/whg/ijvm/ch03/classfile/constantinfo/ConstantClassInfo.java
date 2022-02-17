package com.whg.ijvm.ch03.classfile.constantinfo;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class ConstantClassInfo implements ConstantInfo{

    ConstantPool cp;
    public Uint16 nameIndex;

    ConstantClassInfo(ConstantPool cp){
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
