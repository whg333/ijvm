package com.whg.ijvm.ch09.instruction.base;

import com.whg.ijvm.ch09.heap.RMethod;
import com.whg.ijvm.ch09.runtime.RFrame;
import com.whg.ijvm.ch09.runtime.RThread;
import com.whg.ijvm.ch09.runtime.Slot;

public class MethodInvokeLogic {

    public static void invokeMethod(RFrame invokerFrame, RMethod method){
        RThread thread = invokerFrame.getThread();
        RFrame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);

        int argSlotCount = method.getArgSlotCount();
        if(argSlotCount > 0){
            for(int i=argSlotCount-1;i>=0;i--){
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }

        // 方法调用完毕后都有RETURN，会调用popFrame，所以不用特殊处理，
        // 之前有问题是因为没有切换到栈顶的frame去执行
        // if(!method.isStatic()){
        //     // thread.popFrame();
        // }
        // if(method.getName().equals("<init>")){
        //     thread.popFrame();
        // }
        // if(method.getName().equals("<clinit>")){
        //     thread.popFrame();
        // }

        // TODO delete hack!
        // hack!
        // if(method.isNative()){
        //     if(method.getName().equals("registerNatives")){
        //         thread.popFrame();
        //     } else {
        //         throw new RuntimeException("native method: "+method);
        //     }
        // }
    }

}
