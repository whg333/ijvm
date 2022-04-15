package com.whg.ijvm.ch09.instruction.reserved;

import com.whg.ijvm.ch09.heap.RMethod;
import com.whg.ijvm.ch09.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch09.nativecall.NativeMethod;
import com.whg.ijvm.ch09.nativecall.NativeRegistry;
import com.whg.ijvm.ch09.runtime.RFrame;

public class InvokeNative extends NoOperandsInstruction {

    @Override
    public void execute(RFrame frame) {
        RMethod method = frame.getMethod();
        String className = method.getRClass().getName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();

        NativeMethod nativeMethod = NativeRegistry.findNativeMethod(className, methodName, methodDescriptor);
        if(nativeMethod == null){
            String methodInfo = className + "." + methodName + methodDescriptor;
            throw new RuntimeException("UnsatisfiedLinkError:"+methodInfo);
        }
        // System.out.println("whg InvokeNative: "+NativeRegistry.key(className, methodName, methodDescriptor));
        nativeMethod.invoke(frame);
    }
}
