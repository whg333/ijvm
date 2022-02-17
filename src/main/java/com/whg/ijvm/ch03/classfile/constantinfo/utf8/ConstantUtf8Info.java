package com.whg.ijvm.ch03.classfile.constantinfo.utf8;

import com.whg.ijvm.ch03.classfile.ClassReader;
import com.whg.ijvm.ch03.classfile.constantinfo.ConstantInfo;

public class ConstantUtf8Info implements ConstantInfo {

    public String str;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16().value();
        byte[] bytes = reader.readBytes(length);
        str = new String(bytes);
    }
    
}
