package com.whg.ijvm.ch08.heap.constant;

import com.whg.ijvm.ch08.classfile.constantinfo.member.InterfaceMethodRefInfo;
import com.whg.ijvm.ch08.heap.RClass;
import com.whg.ijvm.ch08.heap.RConstantPool;
import com.whg.ijvm.ch08.heap.RMethod;

public class InterfaceMethodRef extends MemberRef{
    
    RMethod method;

    public InterfaceMethodRef(RConstantPool cp, InterfaceMethodRefInfo refInfo) {
        super(cp, refInfo);
    }

    public RMethod resolveInterfaceMethod(){
        if(method == null){
            resolveInterfaceMethodRef();
        }
        return method;
    }

    private void resolveInterfaceMethodRef() {
        RClass c = resolveClass();
        if(!c.isInterface()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }

        RMethod method = MethodLookup.lookupInterfaceMethod(c, name, descriptor);
        if(method == null){
            throw new RuntimeException("NoSuchMethodError");
        }
        RClass d = cp.getClazz();
        if(!method.isAccessibleTo(d)){
            throw new RuntimeException("IllegalAccessError");
        }

        this.method = method;
    }

}
