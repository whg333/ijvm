package com.whg.ijvm.ch11.nativecall.java;

import com.whg.ijvm.ch11.heap.RClass;
import com.whg.ijvm.ch11.heap.RMethod;
import com.whg.ijvm.ch11.heap.RObject;
import com.whg.ijvm.ch11.nativecall.NativeMethod;
import com.whg.ijvm.ch11.nativecall.NativeRegistry;
import com.whg.ijvm.ch11.runtime.RFrame;
import com.whg.ijvm.ch11.runtime.RThread;

import java.util.List;

public class ThrowableNative {

    private static final String CLASS_NAME = "java/lang/Throwable";

    public static void init() {
        NativeRegistry.register(CLASS_NAME, "fillInStackTrace", "(I)Ljava/lang/Throwable;", fillInStackTrace);
    }

    private static NativeMethod fillInStackTrace = frame -> {
        RObject self = frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(self);
        StackTraceElement[] stackTraceElements = createStackTraceElements(self, frame.getThread());
        self.setExtra(stackTraceElements);
    };

    private static StackTraceElement[] createStackTraceElements(RObject obj, RThread thread) {
        int skip = distanceToObject(obj.getRClass()) + 2;
        List<RFrame> frames =  thread.getFrames();
        frames = frames.subList(skip, frames.size());
        int length = frames.size();
        StackTraceElement[] stackTraceElements = new StackTraceElement[length];
        for(int i=0;i<length;i++){
            stackTraceElements[i] = createStackTraceElement(frames.get(i));
        }
        return stackTraceElements;
    }

    private static StackTraceElement createStackTraceElement(RFrame frame) {
        RMethod method = frame.getMethod();
        RClass clazz = method.getRClass();
        return new StackTraceElement(clazz.getSourceFileName(), clazz.getJavaName(),
                method.getName(), method.getLineNumber(frame.getNextPc()-1));
    }

    private static int distanceToObject(RClass clazz){
        int distance = 0;
        for(RClass c=clazz.getSuperClass();c!=null;c=c.getSuperClass()){
            distance++;
        }
        return distance;
    }

    public static class StackTraceElement{
        String fileName;
        String className;
        String methodName;
        int lineNumber;

        private StackTraceElement(String fileName, String className, String methodName, int lineNumber) {
            this.fileName = fileName;
            this.className = className;
            this.methodName = methodName;
            this.lineNumber = lineNumber;
        }

        @Override
        public String toString() {
            return String.format("%s.%s(%s:%d)",
                    className, methodName, fileName, lineNumber);
        }
    }

}
