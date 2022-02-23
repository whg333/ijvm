package com.whg.ijvm.ch06.instruction;

import com.whg.ijvm.ch06.classfile.MemberInfo;
import com.whg.ijvm.ch06.classfile.attribute.impl.CodeAttribute;
import com.whg.ijvm.ch06.instruction.base.BytecodeReader;
import com.whg.ijvm.ch06.runtime.RFrame;
import com.whg.ijvm.ch06.runtime.RThread;

public class Interpreter {

    public static void run(MemberInfo memberInfo){
        new Interpreter(memberInfo);
    }

    private Interpreter(MemberInfo memberInfo){
        CodeAttribute codeAttr = memberInfo.getCodeAttribute();
        int maxLocals = codeAttr.getMaxLocals().value();
        int maxStack = codeAttr.getMaxStack().value();
        byte[] bytecode = codeAttr.getCode();

        RThread thread = new RThread();
        RFrame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);

        try{
            loop(thread, bytecode);
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

            System.out.printf("pc:%2d inst:%s\n",
                    pc, inst.getClass().getSimpleName());
            inst.execute(frame);
        }
    }

    private void catchErr(Exception e, RFrame frame){
        System.out.printf("LocalVars: %s\n", frame.getLocalVars());
        System.out.printf("OperandStack: %s\n", frame.getOperandStack());
        throw new RuntimeException(e);
    }

}
