package com.whg;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeBoolean {

    private static Unsafe unsafe = null;
    static long flagOffset;

    boolean flag = true;

    // https://blog.csdn.net/qq_34501351/article/details/106139074
    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            flagOffset = unsafe.objectFieldOffset(UnsafeBoolean.class.getDeclaredField("flag"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void m() throws NoSuchFieldException {
        System.out.println("m " + flag);
        System.out.println(unsafe.getByte(this, flagOffset));
        Field flagField = getClass().getDeclaredField("flag");
        Class<?> flagClass = flagField.getType();
        if(flagClass == Boolean.class){
            System.out.println(unsafe.getObject(this, flagOffset));
        }

        System.out.println(unsafe.getBoolean(this, flagOffset));
        System.out.println(unsafe.getBooleanVolatile(this, flagOffset));

        System.out.println();
        flag = false;
        System.out.println("m " + flag);
        System.out.println(unsafe.getByte(this, flagOffset));
        if(flagClass == Boolean.class){
            System.out.println(unsafe.getObject(this, flagOffset));
        }

        System.out.println(unsafe.getBoolean(this, flagOffset));
        System.out.println(unsafe.getBooleanVolatile(this, flagOffset));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ClassLayout.parseClass(UnsafeBoolean.class).toPrintable());
        UnsafeBoolean ub = new UnsafeBoolean();
        System.out.println(ClassLayout.parseInstance(ub).toPrintable());
        ub.m();
        System.out.println(ClassLayout.parseInstance(ub).toPrintable());
    }

}
