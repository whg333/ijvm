package com.whg.ijvm.ch09.nativecall.java;

import com.whg.ijvm.ch09.heap.RClass;
import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.nativecall.NativeMethod;
import com.whg.ijvm.ch09.nativecall.NativeRegistry;

public class ObjectNative {

    private static final String CLASS_NAME = "java/lang/Object";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "getClass", "()Ljava/lang/Class;", getClass);
        NativeRegistry.register(CLASS_NAME, "hashCode", "()I", hashCode);
        NativeRegistry.register(CLASS_NAME, "clone", "()Ljava/lang/Object;", clone);
    }

    private static NativeMethod getClass = frame -> {
        RObject self = frame.getLocalVars().getThis();
        RObject classObj = self.getRClass().getJClass();
        frame.getOperandStack().pushRef(classObj);
    };
    private static NativeMethod hashCode = frame -> {
        RObject self = frame.getLocalVars().getThis();
        int hashCode = self.hashCode();
        frame.getOperandStack().pushInt(hashCode);
    };
    private static NativeMethod clone = frame -> {
        RObject self = frame.getLocalVars().getThis();
        RClass selfClass = self.getRClass();
        RClass cloneable = selfClass.loader.loadClass("java/lang/Cloneable");
        if(!selfClass.isImplements(cloneable)){
            throw new RuntimeException("CloneNotSupportedException");
        }
        RObject clone = self.clone();
        frame.getOperandStack().pushRef(clone);
    };

}
