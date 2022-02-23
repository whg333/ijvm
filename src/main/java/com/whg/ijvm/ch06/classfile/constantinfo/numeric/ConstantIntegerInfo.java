package com.whg.ijvm.ch06.classfile.constantinfo.numeric;

import com.whg.ijvm.ch06.classfile.ClassReader;
import com.whg.ijvm.ch06.classfile.constantinfo.ConstantInfo;

public class ConstantIntegerInfo implements ConstantInfo {

    public int val;

    @Override
    public void readInfo(ClassReader reader) {
       val =  (int)reader.readUint32().value();
    }

}
