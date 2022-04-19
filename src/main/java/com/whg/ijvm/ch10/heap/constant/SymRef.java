package com.whg.ijvm.ch10.heap.constant;

import com.whg.ijvm.ch10.heap.RClass;
import com.whg.ijvm.ch10.heap.RConstantPool;

//symbolic reference
public class SymRef implements Constant{

    protected RConstantPool cp;
    protected String className;
    protected RClass clazz;

    public RClass resolveClass(){
        if(clazz == null){
            resolveClassRef();
        }
        return clazz;
    }

    private void resolveClassRef() {
        RClass d = cp.getClazz();
        RClass c = d.loader.loadClass(className);
        if(!c.isAccessibleTo(d)){
            throw new RuntimeException(String.format("IllegalAccessException: %s -> %s",
                    d.getName(), c.getName()));
        }
        clazz = c;
    }

}
