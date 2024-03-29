package com.whg.ijvm.ch10.classfile.attribute.impl;

import com.whg.ijvm.ch10.classfile.ClassReader;
import com.whg.ijvm.ch10.classfile.ConstantPool;
import com.whg.ijvm.ch10.classfile.attribute.AbstractAttribute;
import com.whg.ijvm.ch10.classfile.attribute.table.LineNumberTableEntry;

public class LineNumberTableAttribute extends AbstractAttribute {

    LineNumberTableEntry[] lineNumberTable;

    public LineNumberTableAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16().value();
        lineNumberTable = new LineNumberTableEntry[length];
        for(int i=0;i<length;i++){
            lineNumberTable[i] = new LineNumberTableEntry(
                    reader.readUint16(),
                    reader.readUint16()
            );
        }
    }

    public int getLineNumber(int pc){
        for(LineNumberTableEntry entry: lineNumberTable){
            if(pc >= entry.getStartPc()){
                return entry.getLineNumber();
            }
        }
        return -1;
    }

}
