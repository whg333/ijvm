package com.whg.ijvm.ch11.heap;

import com.whg.ijvm.ch11.classfile.attribute.table.ExceptionTableEntry;
import com.whg.ijvm.ch11.heap.constant.ClassRef;

public class ExceptionTable {

    ExceptionHandler[] handlers;

    ExceptionTable(ExceptionTableEntry[] entries, RConstantPool cp){
        handlers = new ExceptionHandler[entries.length];
        for(int i=0;i<entries.length;i++){
            ExceptionTableEntry entry = entries[i];
            handlers[i] = new ExceptionHandler(entry, cp);
        }
    }

    public ExceptionHandler findExceptionHandler(RClass exClass, int pc) {
        for(ExceptionHandler handler: handlers){
            if(pc >= handler.startPc && pc < handler.endPc){
                if(handler.catchType == null){
                    return handler; // catch-all
                }
                RClass catchClass = handler.catchType.resolveClass();
                if(catchClass == exClass || catchClass.isSuperClassOf(exClass)){
                    return handler;
                }
            }
        }
        return null;
    }

    public static class ExceptionHandler{
        int startPc;
        int endPc;
        int handlerPc;
        ClassRef catchType;

        public ExceptionHandler(ExceptionTableEntry entry, RConstantPool cp) {
            startPc = entry.getStartPc();
            endPc = entry.getEndPc();
            handlerPc = entry.getHandlerPc();
            catchType = getCatchType(entry.getCatchType(), cp);
        }

        private ClassRef getCatchType(int index, RConstantPool cp) {
            if(index == 0){
                return null;
            }
            return cp.getConstant(index);
        }
    }

}
