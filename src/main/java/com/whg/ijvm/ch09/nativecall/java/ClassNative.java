package com.whg.ijvm.ch09.nativecall.java;

import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.nativecall.NativeMethod;
import com.whg.ijvm.ch09.nativecall.NativeRegistry;

public class ClassNative {

    private static final String CLASS_NAME = "java/lang/Class";

    private static NativeMethod getPrimitiveClass = frame -> {

    };
    private static NativeMethod getName = frame -> {

    };
    private static NativeMethod desiredAssertionStatus0 = frame -> {

    };

    static {
        NativeRegistry.register(CLASS_NAME, "getPrimitiveClass", "(Ljava.lang/String)Ljava/lang/Class", getPrimitiveClass);
        NativeRegistry.register(CLASS_NAME, "getName", "()Ljava/lang/String", getName);
        NativeRegistry.register(CLASS_NAME, "desiredAssertionStatus0", "(Ljava.lang/Class)Z", desiredAssertionStatus0);
    }

}
