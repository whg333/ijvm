package com.whg.ijvm.ch08.heap.constant;

import com.whg.ijvm.ch08.heap.RClass;
import com.whg.ijvm.ch08.heap.RMethod;

public class MethodLookup {

    public static RMethod lookupMethodInClass(RClass clazz, String name, String descriptor){
        for(RClass c=clazz;c!=null;c=c.getSuperClass()){
            for(RMethod method: c.getMethods()){
                if(method.isMatch(name,descriptor)){
                    return method;
                }
            }
        }
        return null;
    }

    public static RMethod lookupMethodInInterfaces(RClass[] ifaces, String name, String descriptor){
        for(RClass iface: ifaces){
            for(RMethod method: iface.getMethods()){
                if(method.isMatch(name, descriptor)){
                    return method;
                }
            }
            RMethod method = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if(method != null){
                return method;
            }
        }
        return null;
    }

    public static RMethod lookupInterfaceMethod(RClass iface, String name, String descriptor) {
        for(RMethod method: iface.getMethods()){
            if(method.isMatch(name, descriptor)){
                return method;
            }
        }
        return lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
    }
}
