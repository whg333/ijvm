package com.whg.ijvm.ch11.test.method;

public class FibonacciTest {

    public static void main(String[] args) {
        long x = fibonacci(30);
        System.out.println(x);
        // System.out.println(FibonacciTest.class.getSimpleName());
    }

    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

}
