package com.whg.ijvm.ch08.instruction;

import com.whg.ijvm.ch08.heap.*;
import com.whg.ijvm.ch08.instruction.base.BytecodeReader;
import com.whg.ijvm.ch08.runtime.RFrame;
import com.whg.ijvm.ch08.runtime.RThread;

public class Interpreter {

    private final RMethod method;
    private boolean logInst;
    private String[] args;

    public static void run(RMethod method, boolean logInst, String[] args){
        Interpreter interpreter = new Interpreter(method, logInst, args);
        interpreter.run();
    }

    private Interpreter(RMethod method, boolean logInst, String[] args){
        this.method = method;
        this.logInst = logInst;
        this.args = args;
    }

    void run(){
        RThread thread = new RThread();
        RFrame frame = thread.newFrame(method);
        thread.pushFrame(frame);

        RObject jArgs = createArgsArray(method.getRClass().loader, args);
        frame.getLocalVars().setRef(0, jArgs); // 局部变量表0的位置预留放命令行参数

        try{
            loop(thread, logInst);
        }catch(Exception e){
            catchErr(e, thread);
        }
    }

    private RObject createArgsArray(RClassLoader loader, String[] args) {
        RClass stringClass = loader.loadClass("java/lang/String");
        RClass arrClass = stringClass.getArrayClass();
        RArray argsArr = arrClass.newArray(args.length);
        RObject[] jArgs = argsArr.getRefs();
        for(int i=0;i<args.length;i++){
            jArgs[i] = StringPool.JString(loader, args[i]);
        }
        return argsArr;
    }

    private void loop(RThread thread, boolean logInst) {
        BytecodeReader reader = new BytecodeReader();
        for(;;){
            RFrame frame = thread.currentFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);

            RMethod method = frame.getMethod();
            reader.reset(method.getCode(), pc);
            short opcode = reader.readUint8().value();
            Instruction inst = InstructionFactory.newInstruction(opcode);
            inst.fetchOperands(reader);
            frame.setNextPc(reader.getPc());

            if(logInst){
                logInstruction(frame, pc, inst);
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

    private void logInstruction(RFrame frame, int pc, Instruction inst){
        // System.out.printf("%d %s >> pc:%2d inst:%s\n", frame.getLevel(), method, pc, inst);

        StringBuilder sb = new StringBuilder();
        int frameLv = frame.getLevel();
        for(int i=0;i<frameLv;i++){
            for(int j=1;j<=4;j++){
                sb.append(' ');
            }
        }
        sb.append(frame.getMethod());

        int sbLen = sb.length();
        int padding = 60-sbLen;
        for(int i=1;i<=padding;i++){
            sb.append(' ');
        }

        sb.append(" >> pc:");
        sb.append(pc);
        if(pc < 10){
            sb.append(' ');
        }

        sb.append(" inst:");
        sb.append(inst);
        System.out.println(sb);
    }

}
