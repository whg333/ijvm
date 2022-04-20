package com.whg.ijvm.ch11.test.exception;

public class StackTraceTest {

    public static void main(String[] args) {
        foo();
    }

    private static void foo() {
        bar();
    }

    private static void bar() {
        throw new RuntimeException("OH!");
    }

}
