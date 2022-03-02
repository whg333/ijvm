package com.whg.ijvm.ch07.classfile.constantinfo.string;

import com.whg.ijvm.ch07.classfile.ClassReader;
import com.whg.ijvm.ch07.classfile.ConstantPool;
import com.whg.ijvm.ch07.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch07.classfile.uint.Uint16;

public class ConstantStringInfo implements ConstantInfo {

    ConstantPool cp;
    Uint16 stringIndex;

    public ConstantStringInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        stringIndex = reader.readUint16();
    }

    public String getString(){
        return cp.getUtf8(stringIndex);
    }

}
