package com.whg.ijvm.ch06.instruction;

import com.whg.ijvm.ch06.heap.RMethod;
import com.whg.ijvm.ch06.instruction.base.BytecodeReader;
import com.whg.ijvm.ch06.instruction.reference.Return;
import com.whg.ijvm.ch06.runtime.RFrame;
import com.whg.ijvm.ch06.runtime.RThread;

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
            catchErr(e, frame);
        }
    }

    private void loop(RThread thread, byte[] bytecode) {
        RFrame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader(bytecode);
        for(;;){
            int pc = frame.getNextPc();
            thread.setPc(pc);
            reader.setPc(pc);

            short opcode = reader.readUint8().value();
            Instruction inst = InstructionFactory.newInstruction(opcode);
            inst.fetchOperands(reader);
            frame.setNextPc(reader.getPc());

            System.out.printf("pc:%2d inst:%s\n", pc, inst);
            if(inst instanceof Return.RETURN){
                break;
            }
            inst.execute(frame);
        }
    }

    private void catchErr(Exception e, RFrame frame){
        System.out.printf("LocalVars: %s\n", frame.getLocalVars());
        System.out.printf("OperandStack: %s\n", frame.getOperandStack());
        throw new RuntimeException(e);
    }

}
