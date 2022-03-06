package com.whg.ijvm.ch08.test;

public class MyObject extends ClassFileTest{

    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768;                      //ldc
        MyObject myObj = new MyObject();    //new
        myObj.X = 'A';
        myObj.a = 3;

        MyObject.staticVar = x;             //putstatic
        x = MyObject.staticVar;             //getstatic

        myObj.instanceVar = x;              //putfield
        x = myObj.instanceVar;              //getfield

        Object obj = myObj;
        if(obj instanceof MyObject){        //instanceof
            myObj = (MyObject) obj;         //checkcast
            System.out.println(myObj.instanceVar);
        }

        System.out.println(myObj.X);
        System.out.println(myObj.a);
    }

}
