package com.whg.ijvm.ch10.nativecall.java;

import com.whg.ijvm.ch10.heap.RArray;
import com.whg.ijvm.ch10.heap.RClass;
import com.whg.ijvm.ch10.heap.RObject;
import com.whg.ijvm.ch10.nativecall.NativeMethod;
import com.whg.ijvm.ch10.nativecall.NativeRegistry;
import com.whg.ijvm.ch10.runtime.LocalVars;

public class SystemNative {

    private static final String CLASS_NAME = "java/lang/System";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "arraycopy",
                "(Ljava/lang/Object;ILjava/lang/Object;II)V", arraycopy);
    }

    private static NativeMethod arraycopy = frame -> {
        LocalVars localVars = frame.getLocalVars();
        RObject src = localVars.getRef(0);
        int srcPos = localVars.getInt(1);
        RObject dest = localVars.getRef(2);
        int destPos = localVars.getInt(3);
        int length = localVars.getInt(4);

        if(src == null || dest == null){
            throw new RuntimeException("NullPointerException");
        }
        if(!checkArrayCopy(src, dest)){
            throw new RuntimeException("ArrayStoreException");
        }
        RArray srcArr = (RArray) src;
        RArray destArr = (RArray) dest;
        if(srcPos < 0 || destPos< 0 || length < 0
            || srcPos+length > srcArr.getArrayLength()
            || destPos+length > destArr.getArrayLength()){
            throw new RuntimeException("IndexOutOfBoundsException");
        }
        RArray.arrayCopy(srcArr, srcPos, destArr, destPos, length);
    };

    private static boolean checkArrayCopy(RObject src, RObject dest) {
        RClass srcClass = src.getRClass();
        RClass destClass = dest.getRClass();
        if(!srcClass.isArray() || !destClass.isArray()){
            return false;
        }
        if(srcClass.getComponentClass().isPrimitive()
                || destClass.getComponentClass().isPrimitive()){
            return srcClass == destClass;
        }
        return true;
    }

}
