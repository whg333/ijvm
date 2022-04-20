package com.whg.ijvm.ch11.classfile.constantinfo.numeric;

import com.whg.ijvm.ch11.classfile.ClassReader;
import com.whg.ijvm.ch11.classfile.constantinfo.ConstantInfo;

public class ConstantIntegerInfo implements ConstantInfo {

    public int val;

    @Override
    public void readInfo(ClassReader reader) {
       val =  (int)reader.readUint32().value();
    }

}
