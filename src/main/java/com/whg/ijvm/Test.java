package com.whg.ijvm;

public class Test {

    private final String name;
    private String str;

    public Test(String name){
        this.name = name;
    }

    public void doSomeThing(){
        // (1) getfield后，astore_1存入局部变量表和aload_1从局部变量表取出来
        // 用作缓存行兑取引用值加速：即局部变量n存储到局部变量表当成缓存，以后直接从缓存取
        final String n = name;
        str = n.substring(1);

        // (2) getfield后直接使用
        // str = name.substring(1);
    }

    public static void main(String[] args) {
        // Test test = new Test("whg");
        // test.doSomeThing();
        // System.out.println(test.str);

        Object obj1 = new Object();
        Class<?> objClass1 = obj1.getClass();
        Object obj2 = new Object();
        Class<?> objClass2 = obj2.getClass();
        System.out.println(objClass1 == objClass2);
        Class<?> objClass = Object.class;
        System.out.println(objClass1 == objClass);
        System.out.println(objClass1);
    }

}
