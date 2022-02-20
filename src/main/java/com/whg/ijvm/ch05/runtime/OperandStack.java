package com.whg.ijvm.ch05.runtime;

import java.util.Arrays;

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
        int low = (int)val;
        int high = (int)(val >> 32);
        slots[nextIdx].num = low;
        slots[nextIdx+1].num = high;
        nextIdx += 2;
    }
    public long popLong(){
        nextIdx -= 2;
        long low = Integer.toUnsignedLong(slots[nextIdx].num);
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

    public void pushSlot(Slot slot){
        slots[nextIdx] = slot;
        nextIdx++;
    }

    public Slot popSlot(){
        nextIdx--;
        return slots[nextIdx];
    }

    @Override
    public String toString() {
        return "{" + nextIdx + " " + Arrays.toString(slots) + "}";
    }
}
