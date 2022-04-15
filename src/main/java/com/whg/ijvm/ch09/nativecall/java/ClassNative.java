package com.whg.ijvm.ch09.nativecall.java;

import com.whg.ijvm.ch09.heap.RClass;
import com.whg.ijvm.ch09.heap.RClassLoader;
import com.whg.ijvm.ch09.heap.RObject;
import com.whg.ijvm.ch09.heap.StringPool;
import com.whg.ijvm.ch09.nativecall.NativeMethod;
import com.whg.ijvm.ch09.nativecall.NativeRegistry;

public class ClassNative {

    private static final String CLASS_NAME = "java/lang/Class";

    public static void init(){
        NativeRegistry.register(CLASS_NAME, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", getPrimitiveClass);
        NativeRegistry.register(CLASS_NAME, "getName0", "()Ljava/lang/String;", getName0);
        NativeRegistry.register(CLASS_NAME, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", desiredAssertionStatus0);
    }

    private static NativeMethod getPrimitiveClass = frame -> {
        RObject nameObj = frame.getLocalVars().getRef(0);
        String name = StringPool.goString(nameObj);

        RClassLoader loader = frame.getMethod().getRClass().loader;
        RObject classObj = loader.loadClass(name).getJClass();

        frame.getOperandStack().pushRef(classObj);
    };
    private static NativeMethod getName0 = frame -> {
        RObject self = frame.getLocalVars().getThis();
        RClass clazz = self.getExtra();

        String javaName = clazz.getJavaName();
        RObject nameObj = StringPool.JString(clazz.loader, javaName);

        frame.getOperandStack().pushRef(nameObj);
    };
    private static NativeMethod desiredAssertionStatus0 = frame -> {
        frame.getOperandStack().pushBoolean(false);
    };

}
