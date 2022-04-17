package com.whg.ijvm.ch09.nativecall.java;

import com.whg.ijvm.ch09.nativecall.NativeMethod;
import com.whg.ijvm.ch09.nativecall.NativeRegistry;

public class DoubleNative {

    private static final String CLASS_NAME = "java/lang/Double";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "doubleToRawLongBits",
                "(D)J", doubleToRawLongBits);
        NativeRegistry.register(CLASS_NAME, "longBitsToDouble",
                "(J)D", longBitsToDouble);
    }

    private static NativeMethod doubleToRawLongBits = frame -> {
        double value = frame.getLocalVars().getDouble(0);
        long bits = Double.doubleToRawLongBits(value);
        frame.getOperandStack().pushLong(bits);
    };
    private static NativeMethod longBitsToDouble = frame -> {
        long bits = frame.getLocalVars().getLong(0);
        double value = Double.longBitsToDouble(bits);
        frame.getOperandStack().pushDouble(value);
    };

}
