package com.whg.ijvm.ch11.nativecall.java;

import com.whg.ijvm.ch11.nativecall.NativeMethod;
import com.whg.ijvm.ch11.nativecall.NativeRegistry;

public class FloatNative {

    private static final String CLASS_NAME = "java/lang/Float";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "floatToRawIntBits",
                "(F)I", floatToRawIntBits);
    }

    private static NativeMethod floatToRawIntBits = frame -> {
        float value = frame.getLocalVars().getFloat(0);
        int bits = Float.floatToRawIntBits(value);
        frame.getOperandStack().pushInt(bits);
    };

}
