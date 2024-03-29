package com.whg.ijvm.ch10.runtime;

import com.whg.ijvm.ch10.heap.RObject;

import java.util.Arrays;

/**
 * 局部变量表，使用索引index访问，所以其实是数组实现
 */
public class LocalVars {

    protected Slot[] slots;

    public LocalVars(int length){
        slots = new Slot[length];
        for(int i=0;i<length;i++){
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int val){
        slots[index].num = val;
    }
    public int getInt(int index){
        return slots[index].num;
    }

    public void setFloat(int index, float val){
        setInt(index, Float.floatToIntBits(val));
    }
    public float getFloat(int index){
        return Float.intBitsToFloat(getInt(index));
    }

    public void setLong(int index, long val){
        // System.out.println(Long.toBinaryString(val));
        int low = (int)val;
        // System.out.println(Integer.toBinaryString(low));
        int high = (int)(val >> 32);
        // System.out.println(Integer.toBinaryString(high));
        slots[index].num = low;
        slots[index+1].num = high;
    }
    public long getLong(int index){
        //低位需要转成无符号的，才可和高位正常组合拼接
        long low = Integer.toUnsignedLong(slots[index].num);
        // System.out.println(Long.toBinaryString(low));
        long high = slots[index+1].num;
        // System.out.println(Long.toBinaryString(high));
        long val = high << 32 | low;
        // System.out.println(Long.toBinaryString(val));
        return val;
    }

    public void setDouble(int index, double val){
        setLong(index, Double.doubleToLongBits(val));
    }
    public double getDouble(int index){
        return Double.longBitsToDouble(getLong(index));
    }

    public void setRef(int index, RObject ref){
        slots[index].ref = ref;
    }
    public RObject getRef(int index){
        return slots[index].ref;
    }

    public void setSlot(int index, Slot slot){
        slots[index].num = slot.num;
        slots[index].ref = slot.ref;
    }

    public RObject getThis(){
        return getRef(0);
    }

    @Override
    public String toString() {
        return Arrays.toString(slots);
    }

}
