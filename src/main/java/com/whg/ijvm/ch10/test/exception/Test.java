package com.whg.ijvm.ch10.test.exception;

public class Test {

    void cantBeZero(int i){
        if(i == 0){
            throw new TestExc();
        }
    }

    void catchOne(){
        try{
            tryItOut();
        }catch(TestExc e){
            handleExc(e);
        }
    }

    private void tryItOut() {
    }

    private void handleExc(TestExc e) {
    }

}
