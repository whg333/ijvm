package com.whg.ijvm.ch11.nativecall.sun.misc;

import com.whg.ijvm.ch11.heap.*;
import com.whg.ijvm.ch11.instruction.base.MethodInvokeLogic;
import com.whg.ijvm.ch11.nativecall.NativeMethod;
import com.whg.ijvm.ch11.nativecall.NativeRegistry;
import com.whg.ijvm.ch11.runtime.OperandStack;

public class VMNative {

    private static final String CLASS_NAME = "sun/misc/VM";

    public static void init(){
        NativeRegistry.register(CLASS_NAME, "initialize", "()V", initialize);
    }

    /*
    private static NativeMethod initialize = frame -> {
        RClass vmClass = frame.getMethod().getRClass();
        RObject savedProps = vmClass.getRefVar("savedProps", "Ljava/util/Properties;");
        RObject key = StringPool.JString(vmClass.loader, "foo");
        RObject value = StringPool.JString(vmClass.loader, "bar");

        OperandStack stack = frame.getOperandStack();
        stack.pushRef(savedProps);
        stack.pushRef(key);
        stack.pushRef(value);

        RClass propsClass = vmClass.loader.loadClass("java/util/Properties");
        RMethod setPropMethod = propsClass.getInstanceMethod("setProperty",
                "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
        MethodInvokeLogic.invokeMethod(frame, setPropMethod);
    };
    */

    private static NativeMethod initialize = frame -> {
        RClassLoader loader = frame.getMethod().getRClass().loader;
        RClass jlSysClass = loader.loadClass("java/lang/System");
        RMethod initSysClassMethod = jlSysClass.getStaticMethod("initializeSystemClass", "()V");
        MethodInvokeLogic.invokeMethod(frame, initSysClassMethod);
    };

}
