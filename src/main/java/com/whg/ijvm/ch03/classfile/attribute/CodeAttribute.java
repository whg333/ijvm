package com.whg.ijvm.ch03.classfile.attribute;

import com.whg.ijvm.ch03.classfile.ClassFile;
import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.ConstantPool;
import com.whg.ijvm.ch03.classfile.attribute.table.ExceptionTableEntry;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class CodeAttribute extends AbstractAttribute{

    Uint16 maxStack;
    Uint16 maxLocals;
    byte[] code;
    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributes;

    CodeAttribute(String name, int length, ConstantPool cp) {
        super(name, length, cp);
    }

    @Override
    public void readInfo(ClassReader reader) {
        maxStack = reader.readUint16();
        maxLocals = reader.readUint16();

        int codeLength = (int) reader.readUint32().value();
        code = reader.readBytes(codeLength);

        exceptionTable = readExceptionTable(reader);
        attributes = ClassFile.readAttributes(reader, cp);
    }

    private ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        int exceptionTableLength = reader.readUint16().value();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for(int i=0;i<exceptionTableLength;i++){
            exceptionTable[i] = new ExceptionTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
            );
        }
        return exceptionTable;
    }

}
