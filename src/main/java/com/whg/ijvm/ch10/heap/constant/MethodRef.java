package com.whg.ijvm.ch10.heap.constant;

import com.whg.ijvm.ch10.classfile.constantinfo.member.MethodRefInfo;
import com.whg.ijvm.ch10.heap.RClass;
import com.whg.ijvm.ch10.heap.RConstantPool;
import com.whg.ijvm.ch10.heap.RMethod;

public class MethodRef extends MemberRef{

    RMethod method;

    public MethodRef(RConstantPool cp, MethodRefInfo refInfo) {
        super(cp, refInfo);
    }

    public RMethod resolveMethod(){
        if(method == null){
            resolveMethodRef();
        }
        return method;
    }

    private void resolveMethodRef() {
        RClass c = resolveClass();
        if(c.isInterface()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }

        RMethod method = lookupMethod(c, name, descriptor);
        if(method == null){
            throw new RuntimeException("NoSuchMethodError");
        }
        RClass d = cp.getClazz();
        if(!method.isAccessibleTo(d)){
            throw new RuntimeException("IllegalAccessError");
        }

        this.method = method;
    }

    private RMethod lookupMethod(RClass clazz, String name, String descriptor) {
        RMethod method = MethodLookup.lookupMethodInClass(clazz, name, descriptor);
        if(method == null){
            method = MethodLookup.lookupMethodInInterfaces(clazz.getInterfaces(), name, descriptor);
        }
        return method;
    }

}
