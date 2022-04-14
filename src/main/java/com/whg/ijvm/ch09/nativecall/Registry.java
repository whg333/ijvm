package com.whg.ijvm.ch09.nativecall;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private static final Map<String, NativeMethod> registry = new HashMap<>();
    private static final NativeMethod emptyNativeMethod = new NativeMethod();

    public void register(String className, String methodName, String methodDesc, NativeMethod method){
        String key = key(className, methodName, methodDesc);
        registry.put(key, method);
    }

    public NativeMethod findNativeMethod(String className, String methodName, String methodDesc){
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

    private String key(String className, String methodName, String methodDesc){
        return className+"~"+methodName+"~"+methodDesc;
    }

}
