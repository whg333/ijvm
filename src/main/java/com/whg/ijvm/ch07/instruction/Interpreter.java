package com.whg.ijvm.ch07.instruction;

import com.whg.ijvm.ch07.heap.RMethod;
import com.whg.ijvm.ch07.instruction.base.BytecodeReader;
import com.whg.ijvm.ch07.instruction.control.Return;
import com.whg.ijvm.ch07.runtime.RFrame;
import com.whg.ijvm.ch07.runtime.RThread;

public class Interpreter {

    public static void run(RMethod method){
        new Interpreter(method);
    }

    private Interpreter(RMethod method){
        RThread thread = new RThread();
        RFrame frame = thread.newFrame(method);
        thread.pushFrame(frame);

        try{
            loop(thread, method.getCode());
        }catch (Exception e){
            catchErr(e, thread);
        }
    }

    private void loop(RThread thread, byte[] bytecode) {
        BytecodeReader reader = new BytecodeReader(bytecode);
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

            System.out.printf("pc:%2d inst:%s\n", pc, inst);

            inst.execute(frame);

            // if(inst instanceof Return.RETURN){
            //     System.out.println("RETURN");
            //     break;
            // }
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

}
