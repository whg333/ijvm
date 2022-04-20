package com.whg.ijvm.ch11;

import com.whg.ijvm.ch11.classpath.Classpath;
import com.whg.ijvm.ch11.heap.RClass;
import com.whg.ijvm.ch11.heap.RClassLoader;
import com.whg.ijvm.ch11.heap.RMethod;
import com.whg.ijvm.ch11.instruction.Interpreter;
import com.whg.ijvm.ch11.nativecall.NativeRegistry;
import com.whg.ijvm.ch11.runtime.RThread;

public class Jvm {

    JvmCmder cmd;
    RClassLoader classLoader;
    RThread mainThread;

    public Jvm(JvmCmder cmd, Classpath cp) {
        this.cmd = cmd;
        this.classLoader = new RClassLoader(cp, cmd.verboseClassFlag());
        this.mainThread = new RThread();
    }

    void start(){
        NativeRegistry.init(); // 初始化注册本地方法
        initVM();
        execMain();
    }

    private void initVM() {
        RClass vmClass = classLoader.loadClass("sun/misc/VM");
        vmClass.init(mainThread);
        new Interpreter(mainThread, cmd.versionFlag())
                .run();
    }

    private void execMain() {
        String className = cmd.className();
        RClass mainClass = classLoader.loadClass(className);
        RMethod mainMethod = mainClass.getMainMethod();
        if(mainMethod == null){
            System.out.printf("Main method not found in class:[%s]%n", className);
            return;
        }

        new Interpreter(mainThread, cmd.versionFlag())
                .runMethod(mainMethod, cmd.args());
    }

}
