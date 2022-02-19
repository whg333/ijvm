package com.whg.ijvm.ch04.runtime;

public class LocalVars {

    Slot[] slots;

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
        long low = val;
        long high = val >> 32;
        slots[index].num = (int)low;
        slots[index+1].num = (int)high;
    }
    public long getLong(int index){
        long low = slots[index].num;
        long high = slots[index+1].num;
        return high << 32 | low;
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

}
