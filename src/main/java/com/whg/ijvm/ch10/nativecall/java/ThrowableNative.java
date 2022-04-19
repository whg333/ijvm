package com.whg.ijvm.ch10.nativecall.java;

import com.whg.ijvm.ch10.heap.RClass;
import com.whg.ijvm.ch10.heap.RObject;
import com.whg.ijvm.ch10.nativecall.NativeMethod;
import com.whg.ijvm.ch10.nativecall.NativeRegistry;
import com.whg.ijvm.ch10.runtime.RThread;

public class ThrowableNative {

    private static final String CLASS_NAME = "java/lang/Throwable";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "fillInStackTrace", "(I)Ljava/lang/Throwable;", fillInStackTrace);
    }

    private static NativeMethod fillInStackTrace = frame -> {
        RObject self = frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(self);
        RObject stes = createStackTraceElements(self, frame.getThread());
        self.setExtraObj(stes);
    };

    private static RObject createStackTraceElements(RObject self, RThread thread) {
        return null;
    }

    class StackTraceElement{
        String fileName;
        String className;
        String methodName;
        int lineNumber;
    }

}
