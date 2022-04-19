package com.whg.ijvm.ch10.test.exception;

public class Test {

    void cantBeZero(int i){
        if(i == 0){
            throw new TestExc();
        }
    }

}
