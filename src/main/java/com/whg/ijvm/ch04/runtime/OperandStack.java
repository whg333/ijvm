package com.whg.ijvm.ch04.runtime;

public class OperandStack {

    Slot[] slots;
    int nextIdx; //下一个操作的索引

    public OperandStack(int length){
        slots = new Slot[length];
        for(int i=0;i<length;i++){
            slots[i] = new Slot();
        }
    }

    public void pushInt(int val){
        slots[nextIdx].num = val;
        nextIdx++;
    }
    public int popInt(){
        nextIdx--;
        return slots[nextIdx].num;
    }

    public void pushFloat(float val){
        pushInt(Float.floatToIntBits(val));
    }
    public float popFloat(){
        return Float.intBitsToFloat(popInt());
    }

    public void pushLong(long val){
        long low = val;
        long high = val >> 32;
        slots[nextIdx].num = (int)low;
        slots[nextIdx+1].num = (int)high;
        nextIdx += 2;
    }
    public long popLong(){
        nextIdx -= 2;
        long low = slots[nextIdx].num;
        long high = slots[nextIdx+1].num;
        return high << 32 | low;
    }

    public void pushDouble(double val){
        pushLong(Double.doubleToLongBits(val));
    }
    public double popDouble(){
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(RObject ref){
        slots[nextIdx].ref = ref;
        nextIdx++;
    }
    public RObject popRef(){
        nextIdx--;
        RObject ref = slots[nextIdx].ref;
        slots[nextIdx].ref = null;
        return ref;
    }

}
