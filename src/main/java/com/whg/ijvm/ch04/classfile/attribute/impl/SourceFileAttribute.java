package com.whg.ijvm.ch04.classfile.attribute.impl;

import com.whg.ijvm.ch04.classfile.ClassReader;
import com.whg.ijvm.ch04.classfile.ConstantPool;
import com.whg.ijvm.ch04.classfile.attribute.AbstractAttribute;
import com.whg.ijvm.ch04.classfile.uint.Uint16;

public class SourceFileAttribute extends AbstractAttribute {

    Uint16 sourceFileIndex;

    public SourceFileAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        sourceFileIndex = reader.readUint16();
    }

    public String getFileName(){
        return cp.getUtf8(sourceFileIndex);
    }

}
