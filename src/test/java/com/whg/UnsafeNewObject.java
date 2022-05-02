package com.whg;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeNewObject {

    private static Unsafe unsafe = null;

    // https://blog.csdn.net/qq_34501351/article/details/106139074
    static {
        try {
            Field getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class A{
        A a = new A("inner a");
        String name;
        A(String name){
            this.name = name;
        }
        // void init(){
        //     a = new A("inner a");
        // }
    }

    public static void main(String[] args) throws InstantiationException {
        // A a = new A("main");
        // a.init();

        A a = (A) unsafe.allocateInstance(A.class);
        a.name = "main";

        System.out.println(a.name);
    }

}
