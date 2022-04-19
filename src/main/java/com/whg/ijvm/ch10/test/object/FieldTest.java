package com.whg.ijvm.ch10.test.object;

public class FieldTest {

    public static class Inner {
        private int x;
    }

    public static void main(String[] args) {
        Inner inner = new Inner();
        inner.x = 100;
        System.out.println(inner.x);
    }

}
