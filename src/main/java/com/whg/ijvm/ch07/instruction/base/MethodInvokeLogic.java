package com.whg.ijvm.ch07.instruction.base;

import com.whg.ijvm.ch07.heap.RMethod;
import com.whg.ijvm.ch07.runtime.RFrame;
import com.whg.ijvm.ch07.runtime.RThread;
import com.whg.ijvm.ch07.runtime.Slot;

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
    }

}
