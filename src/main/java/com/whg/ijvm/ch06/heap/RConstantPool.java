package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ConstantPool;
import com.whg.ijvm.ch06.classfile.constantinfo.ConstantInfo;

public class RConstantPool {

    public RConstantPool(RClass clazz, ConstantPool cp) {
        ConstantInfo[] constantInfos = cp.getInfos();
    }

}
