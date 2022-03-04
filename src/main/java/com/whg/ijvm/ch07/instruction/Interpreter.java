package com.whg.ijvm.ch07.instruction;

import com.whg.ijvm.ch07.heap.RMethod;
import com.whg.ijvm.ch07.instruction.base.BytecodeReader;
import com.whg.ijvm.ch07.runtime.RFrame;
import com.whg.ijvm.ch07.runtime.RThread;

public class Interpreter {

    public static void run(RMethod method, boolean logInst){
        new Interpreter(method, logInst);
    }

    private Interpreter(RMethod method, boolean logInst){
        RThread thread = new RThread();
        RFrame frame = thread.newFrame(method);
        thread.pushFrame(frame);

        try{
            loop(thread, logInst);
        }catch (Exception e){
            catchErr(e, thread);
        }
    }

    private void loop(RThread thread, boolean logInst) {
        BytecodeReader reader = new BytecodeReader();
        for(;;){
            RFrame frame = thread.currentFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);
            reader.setPc(pc);

            reader.reset(frame.getMethod().getCode(), pc);
            short opcode = reader.readUint8().value();
            Instruction inst = InstructionFactory.newInstruction(opcode);
            inst.fetchOperands(reader);
            frame.setNextPc(reader.getPc());

            if(logInst){
                System.out.printf("%s >> pc:%2d inst:%s\n", new Executor(frame), pc, inst);
            }

            inst.execute(frame);
            if(thread.isStackEmpty()){
                break;
            }
        }
    }

    private void catchErr(Exception e, RThread thread){
        for(;!thread.isStackEmpty();){
            RFrame frame = thread.popFrame();
            RMethod method = frame.getMethod();
            String className = method.getRClass().getName();
            System.out.printf(">> pc:%4d %s.%s%s \n", frame.getNextPc(), className,
                    method.getName(), method.getDescriptor());
            // System.out.printf("LocalVars: %s\n", frame.getLocalVars());
            // System.out.printf("OperandStack: %s\n", frame.getOperandStack());
        }
        throw new RuntimeException(e);
    }

    private static class Executor{
        final String className;
        final String methodName;
        final String methodDesc;
        public Executor(RFrame frame) {
            RMethod method = frame.getMethod();
            String name = method.getRClass().getName();
            this.className = name.substring(name.lastIndexOf('/')+1);
            this.methodName = method.getName();
            this.methodDesc = method.getDescriptor();
        }
        @Override
        public String toString() {
            return String.format("%s.%s%s", className, methodName, methodDesc);
        }
    }

}
