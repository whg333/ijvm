package com.whg.ijvm.ch08.heap;

import com.whg.ijvm.ch08.classfile.ClassFile;
import com.whg.ijvm.ch08.classpath.ClassData;
import com.whg.ijvm.ch08.classpath.Classpath;

import java.util.HashMap;
import java.util.Map;

public class RClassLoader {

    Classpath classpath;
    boolean verboseFlag;
    Map<String, RClass> classMap;

    public RClassLoader(Classpath classpath, boolean verboseFlag) {
        this.classpath = classpath;
        this.verboseFlag = verboseFlag;
        classMap = new HashMap<>();
    }

    public RClass loadClass(String className){
        RClass clazz = classMap.get(className);
        if(clazz != null){
            return clazz; //已经加载
        }
        if(RClass.isArray(className)){
            return loadArrayClass(className);
        }
        return loadNonArrayClass(className);
    }

    private RClass loadArrayClass(String className){
        RClass arrayClass = new RClass(className, this);
        classMap.put(className, arrayClass);
        return arrayClass;
    }

    private RClass loadNonArrayClass(String className) {
        ClassData classData = readClass(className);
        RClass clazz = defineClass(classData.bytes);
        link(clazz);
        if(verboseFlag){
            System.out.printf("[Loaded %s from %s]\n", className, classData.entry);
        }
        return clazz;
    }

    private ClassData readClass(String className){
        ClassData classData = classpath.readClass(className);
        if(classData == null){
            throw new RuntimeException("ClassNotFoundException: "+className);
        }
        return classData;
    }

    private RClass defineClass(byte[] bytes) {
        RClass clazz = parseClass(bytes);
        classMap.put(clazz.name, clazz);
        return clazz;
    }

    private RClass parseClass(byte[] bytes) {
        ClassFile classFile = ClassFile.parse(bytes);
        RClass clazz = new RClass(classFile, this);
        clazz.resolveSuperClass();
        clazz.resolveInterfaces();
        return clazz;
    }

    private void link(RClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void verify(RClass clazz) {

    }

    private void prepare(RClass clazz) {
        clazz.calcInstanceFieldSlotIds();
        clazz.calcStaticFieldSlotIds();
        clazz.allocAndInitStaticVars();
    }

}
