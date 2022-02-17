package com.whg.ijvm.ch03.classfile.attribute;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.attribute.table.LocalVariableTableEntry;

public class LocalVariableTableAttribute extends AbstractAttribute{

    LocalVariableTableEntry[] localVariableTable;

    LocalVariableTableAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16().value();
        localVariableTable = new LocalVariableTableEntry[length];
        for(int i=0;i<length;i++){
            localVariableTable[i] = new LocalVariableTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
            );
        }
    }

}
