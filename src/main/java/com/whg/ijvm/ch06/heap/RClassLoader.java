package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.ClassFile;
import com.whg.ijvm.ch06.classpath.ClassData;
import com.whg.ijvm.ch06.classpath.Classpath;

import java.util.HashMap;
import java.util.Map;

public class RClassLoader {

    Classpath classpath;
    Map<String, RClass> classMap;

    public RClassLoader(Classpath classpath) {
        this.classpath = classpath;
        classMap = new HashMap<>();
    }

    public RClass loadClass(String className){
        RClass clazz = classMap.get(className);
        if(clazz != null){
            return clazz;
        }
        return loadNonArrayClass(className);
    }

    private RClass loadNonArrayClass(String className) {
        ClassData classData = readClass(className);
        RClass clazz = defineClass(classData.bytes);
        link(clazz);
        System.out.printf("[Loaded %s from %s]", className, classData.entry);
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
        return new RClass(classFile, this);
    }

    private void link(RClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void verify(RClass clazz) {

    }

    private void prepare(RClass clazz) {

    }

}
