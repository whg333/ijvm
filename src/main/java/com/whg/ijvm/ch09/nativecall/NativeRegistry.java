package com.whg.ijvm.ch09.nativecall;

import com.whg.ijvm.ch09.nativecall.java.ClassNative;
import com.whg.ijvm.ch09.nativecall.java.ObjectNative;

import java.util.HashMap;
import java.util.Map;

public class NativeRegistry {

    private static final Map<String, NativeMethod> registry = new HashMap<>();
    private static final NativeMethod emptyNativeMethod = frame -> {

    };

    public static void init() {
        ClassNative.init();
        ObjectNative.init();
    }

    public static void register(String className, String methodName, String methodDesc, NativeMethod method){
        String key = key(className, methodName, methodDesc);
        registry.put(key, method);
    }

    public static NativeMethod findNativeMethod(String className, String methodName, String methodDesc){
        String key = key(className, methodName, methodDesc);
        NativeMethod method = registry.get(key);
        if(method != null){
            return method;
        }
        if(methodDesc.equals("()V") && methodName.equals("registerNatives")){
            return emptyNativeMethod;
        }
        return null;
    }

    public static String key(String className, String methodName, String methodDesc){
        return className+"~"+methodName+"~"+methodDesc;
    }

}
