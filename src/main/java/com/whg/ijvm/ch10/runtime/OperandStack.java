package com.whg.ijvm.ch10.runtime;

import com.whg.ijvm.ch10.heap.RArray;
import com.whg.ijvm.ch10.heap.RObject;

import java.util.Arrays;

/**
 * 操作数栈，有常规的push入栈和pop出栈操作，根据类型区分
 */
public class OperandStack {

    Slot[] slots;
    int nextIdx; //下一个操作的索引

    public OperandStack(int length){
        slots = new Slot[length];
        init(length);
    }

    public void clear() {
        init(slots.length);
    }

    private void init(int length){
        nextIdx = 0;
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

    public RArray popArrRef(){
        return (RArray)popRef();
    }

    public void pushSlot(Slot slot){
        // slots[nextIdx] = slot;
        // 不能使用上面引用的方式，而应该如下设置slot的值，
        // 否则当执行DUP（拷贝相同的slot）后再执行INVOKE_SPECIAL，popRef的时候，因为DUP是指向同一个引用，导致栈上2个ref改变为null
        slots[nextIdx].num = slot.num;
        slots[nextIdx].ref = slot.ref;
        nextIdx++;
    }

    public Slot popSlot(){
        nextIdx--;
        // 调用clone生成一个拷贝体，因为如果直接返回引用，则会被修改产生副作用
        // 例如DUP_X1指令代表复制栈顶的值，此时的复制就应该是复制值对象，而不是返回对象引用
        return slots[nextIdx].clone();
    }

    public RObject getRefFromTop(int n) {
        int index = nextIdx-1-n;
        if(index < 0){
            System.out.println("getRefFromTop:"+index);
            return null;
        }
        return slots[index].ref;
    }

    public void pushBoolean(boolean val){
        if(val){
            pushInt(1);
        }else{
            pushInt(0);
        }
    }
    public boolean popBoolean(){
        return popInt() == 1;
    }

    @Override
    public String toString() {
        return "{" + nextIdx + " " + Arrays.toString(slots) + "}";
    }

}
