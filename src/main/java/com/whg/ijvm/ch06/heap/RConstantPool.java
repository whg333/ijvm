package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ConstantPool;
import com.whg.ijvm.ch06.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantDoubleInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantFloatInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantIntegerInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantLongInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.string.ConstantStringInfo;
import com.whg.ijvm.ch06.classfile.uint.Uint8;
import com.whg.ijvm.ch06.heap.constant.*;

public class RConstantPool {

    RClass clazz;
    Constant[] constants;

    public RConstantPool(RClass clazz, ConstantPool cp) {
        ConstantInfo[] infos = cp.getInfos();
        constants = new Constant[infos.length];
        for(int i=0;i<infos.length;i++){
            ConstantInfo info = infos[i];
            if (info instanceof ConstantIntegerInfo) {
                constants[i] = new ConstantInteger((ConstantIntegerInfo)info);
            }else if(info instanceof ConstantFloatInfo) {
                constants[i] = new ConstantFloat((ConstantFloatInfo)info);
            }else if(info instanceof ConstantLongInfo) {
                constants[i] = new ConstantLong((ConstantLongInfo)info);
                i++;
            }else if(info instanceof ConstantDoubleInfo) {
                constants[i] = new ConstantDouble((ConstantDoubleInfo)info);
                i++;
            }else if(info instanceof ConstantStringInfo) {
                constants[i] = new ConstantString((ConstantStringInfo)info);
            }
        }
    }

    Constant getConstant(Uint8 index){
        return getConstant(index.value());
    }

    <T extends Constant> T getConstant(int index){
        return (T) constants[index];
    }

}
