package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ConstantPool;
import com.whg.ijvm.ch06.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.member.ClassInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.member.FieldRefInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.member.InterfaceMethodRefInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.member.MethodRefInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantDoubleInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantFloatInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantIntegerInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantLongInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.string.ConstantStringInfo;
import com.whg.ijvm.ch06.classfile.uint.Uint8;
import com.whg.ijvm.ch06.heap.constant.*;

public class RConstantPool {

    public RClass clazz;
    Constant[] constants;

    public RConstantPool(RClass clazz, ConstantPool cp) {
        this.clazz = clazz;

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
            }else if(info instanceof ClassInfo) {
                constants[i] = new ClassRef(this, (ClassInfo)info);
            }else if(info instanceof FieldRefInfo) {
                constants[i] = new FieldRef(this, (FieldRefInfo)info);
            }else if(info instanceof MethodRefInfo) {
                constants[i] = new MethodRef(this, (MethodRefInfo)info);
            }else if(info instanceof InterfaceMethodRefInfo) {
                constants[i] = new InterfaceMethodRef(this, (InterfaceMethodRefInfo)info);
            }
        }
    }

    <T extends Constant> T getConstant(Uint8 index){
        return getConstant(index.value());
    }

    <T extends Constant> T getConstant(int index){
        return (T) constants[index];
    }

}
