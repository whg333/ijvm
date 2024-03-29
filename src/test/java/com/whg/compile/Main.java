package com.whg.compile;

public class Main {

    // https://blog.csdn.net/qq_36370187/article/details/113106282
    public static void main(String[] args) {
        String input = "(add 2 (subtract 34 29))";
        System.out.println("input="+input);
        String output = new Compiler().compile(input);
        System.out.println("output="+output);

        String expectOutput = "add(2, subtract(34, 29))";
        if(output.equals(expectOutput)){
            System.out.println("compile correct!");
        }else{
            throw new AssertionError("compile error!");
        }
    }

}
