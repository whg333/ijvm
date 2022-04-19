package com.whg.ijvm.ch10.test.exception;

public class Test {

    public static void main(String[] args) {
        System.out.println(test());
    }

    static int test(){
        int a = 1;
        try{
            a = 2;
        }catch(Exception e){
            a = 3;
        }finally{
            a = 4;
        }
        // a = 5;
        return a;
    }

}
