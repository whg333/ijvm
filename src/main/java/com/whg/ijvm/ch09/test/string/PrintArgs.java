package com.whg.ijvm.ch09.test.string;

public class PrintArgs {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

}
