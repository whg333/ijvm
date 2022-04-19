package com.whg.ijvm.ch10.instruction.reference;

import com.whg.ijvm.ch10.heap.RObject;
import com.whg.ijvm.ch10.heap.StringPool;
import com.whg.ijvm.ch10.instruction.base.NoOperandsInstruction;
import com.whg.ijvm.ch10.nativecall.java.ThrowableNative.StackTraceElement;
import com.whg.ijvm.ch10.runtime.OperandStack;
import com.whg.ijvm.ch10.runtime.RFrame;
import com.whg.ijvm.ch10.runtime.RThread;

public class Athrow extends NoOperandsInstruction {

    @Override
    public void execute(RFrame frame) {
        RObject exception = frame.getOperandStack().popRef();
        if(exception == null){
            throw new RuntimeException("NullPointerException");
        }
        RThread thread = frame.getThread();
        if(!findAndGotoExceptionHandler(thread, exception)){
            handleUncaughtException(thread, exception);
        }
    }

    private boolean findAndGotoExceptionHandler(RThread thread, RObject exception) {
        do {
            RFrame frame = thread.currentFrame();
            int pc = frame.getNextPc() - 1;
            int handlerPc = frame.getMethod().findExceptionHandlerPc(exception.getRClass(), pc);
            if (handlerPc > 0) {
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushRef(exception);
                frame.setNextPc(handlerPc);
                return true;
            }

            thread.popFrame();
        } while (!thread.isStackEmpty());
        return false;
    }

    private void handleUncaughtException(RThread thread, RObject exception) {
        thread.clearStack();

        RObject detailMessageObj = exception.getRefVar("detailMessage", "Ljava/lang/String;");
        String detailMessage = StringPool.goString(detailMessageObj);
        System.out.println(exception.getRClass().getJavaName() + ": " + detailMessage);

        Object extra = exception.getExtra(); // extra存放的是虚拟机栈信息，在ThrowableNative本地方法fillInStackTrace处理
        StackTraceElement[] stackTraceElements = (StackTraceElement[]) extra;
        for(StackTraceElement element: stackTraceElements){
            System.out.println("\tat "+element);
        }
    }

}
