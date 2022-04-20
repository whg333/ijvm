package com.whg.ijvm.ch11.nativecall.java;

import com.whg.ijvm.ch11.heap.RObject;
import com.whg.ijvm.ch11.heap.StringPool;
import com.whg.ijvm.ch11.nativecall.NativeMethod;
import com.whg.ijvm.ch11.nativecall.NativeRegistry;

public class StringNative {

    private static final String CLASS_NAME = "java/lang/String";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "intern",
                "()Ljava/lang/String;", intern);
    }

    private static NativeMethod intern = frame -> {
        RObject self = frame.getLocalVars().getThis();
        RObject interned = StringPool.internString(self);
        frame.getOperandStack().pushRef(interned);
    };

}
