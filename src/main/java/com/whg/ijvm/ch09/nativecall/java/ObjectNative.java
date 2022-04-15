package com.whg.ijvm.ch09.nativecall.java;

import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.nativecall.NativeMethod;
import com.whg.ijvm.ch09.nativecall.NativeRegistry;

public class ObjectNative {

    private static final String CLASS_NAME = "java/lang/Object";

    private static NativeMethod getClass = frame -> {
        RObject self = frame.getLocalVars().getThis();
        RObject classObj = self.getRClass().getJClass();
        frame.getOperandStack().pushRef(classObj);
    };

    static {
        NativeRegistry.register(CLASS_NAME, "getClass", "()Ljava/lang/Class", getClass);
    }

}
