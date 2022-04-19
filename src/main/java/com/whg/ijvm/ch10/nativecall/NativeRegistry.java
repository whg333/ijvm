package com.whg.ijvm.ch10.nativecall;

import com.whg.ijvm.ch10.nativecall.java.*;
import com.whg.ijvm.ch10.nativecall.sun.misc.VMNative;

import java.util.HashMap;
import java.util.Map;

public class NativeRegistry {

    private static final Map<String, NativeMethod> registry = new HashMap<>();
    private static final NativeMethod emptyNativeMethod = frame -> {

    };

    public static void init() {
        ClassNative.init();
        DoubleNative.init();
        FloatNative.init();
        ObjectNative.init();
        StringNative.init();
        SystemNative.init();

        VMNative.init();
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
            return emptyNativeMethod; // 暂未实现的本地方法返回空执行体
        }
        return null;
    }

    public static String key(String className, String methodName, String methodDesc){
        return className+"~"+methodName+"~"+methodDesc;
    }

}
