package com.whg.ijvm.ch07.test;

public class FibonacciTest {

    public static void main(String[] args) {
        long x = fibonacci(20);
        System.out.println(x);
    }

    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

}
