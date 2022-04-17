package com.whg.ijvm.ch09.heap;

import com.whg.ijvm.ch09.classfile.ClassFile;
import com.whg.ijvm.ch09.classpath.ClassData;
import com.whg.ijvm.ch09.classpath.Classpath;

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

        loadBasicClasses();
        loadPrimitiveClasses();
    }

    private void loadBasicClasses(){
        RClass javaLangClass = loadClass(javaLangClassName()); // 触发java.lang.Object等类和接口的加载
        classMap.forEach((className, clazz) -> {
            if(clazz.getJClass() == null){
                clazz.setJClass(javaLangClass.newObject());
                clazz.getJClass().setExtra(clazz);
            }
        });
    }

    private void loadPrimitiveClasses(){
        for(String primitiveType: RArray.primitiveTypeKeys()){
            loadPrimitiveClass(primitiveType);
        }
    }

    private void loadPrimitiveClass(String primitiveName){
        RClass clazz = newPrimitiveClass(primitiveName);
        RClass javaLangClass = classMap.get(javaLangClassName());
        if(javaLangClass != null){
            clazz.setJClass(javaLangClass.newObject());
            clazz.getJClass().setExtra(clazz);
            classMap.put(primitiveName, clazz);
        }
    }

    // 基本类型没有超类也没有实现任何接口
    private RClass newPrimitiveClass(String primitiveName){
        RClass clazz = new RClass();
        clazz.loader = this;

        clazz.accessFlags = AccessFlags.ACC_PUBLIC;
        clazz.name = primitiveName;

        clazz.init = true;
        return clazz;
    }

    public RClass loadClass(String className){
        RClass clazz = classMap.get(className);
        if(clazz != null){
            return clazz; //已经加载
        }
        if(RClass.isArray(className)){
            clazz = loadArrayClass(className);
        }else{
            clazz = loadNonArrayClass(className);
        }

        RClass javaLangClass = classMap.get(javaLangClassName());
        if(javaLangClass != null){
            clazz.setJClass(javaLangClass.newObject());
            clazz.getJClass().setExtra(clazz);
        }
        return clazz;
    }

    private String javaLangClassName(){
        return "java/lang/Class";
    }

    private RClass loadArrayClass(String className){
        RClass clazz = newArrayClass(className);
        classMap.put(className, clazz);
        return clazz;
    }

    private RClass newArrayClass(String arrayName){
        RClass clazz = new RClass();
        clazz.loader = this;

        clazz.accessFlags = AccessFlags.ACC_PUBLIC;
        clazz.name = arrayName;
        clazz.superClass = this.loadClass("java/lang/Object");
        clazz.interfaces = new RClass[]{
                this.loadClass("java/lang/Cloneable"),
                this.loadClass("java/io/Serializable"),
        };

        clazz.init = true;
        return clazz;
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
