package com.whg.ijvm.ch07.heap;

public class RMethodDescriptor {

    String[] parameterTypes;
    String returnType;

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public static RMethodDescriptor parse(String descriptor){
        //TODO impl
        return new RMethodDescriptor();
    }

}
