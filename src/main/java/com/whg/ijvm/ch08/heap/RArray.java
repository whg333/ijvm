package com.whg.ijvm.ch08.heap;

public class RArray extends RObject{

    RArray(String name, int count){
        switch(name){
            case "[Z":
            case "[B":
                data = new byte[count];
                break;
            case "[C":
                data = new char[count];
                break;
            case "[S":
                data = new short[count];
                break;
            case "[I":
                data = new int[count];
                break;
            case "[J":
                data = new long[count];
                break;
            case "[F":
                data = new float[count];
                break;
            case "[D":
                data = new double[count];
                break;
            default:
                data = new RObject[count];
                break;
        }
    }

    public int getArrayLength(){
        if(data instanceof byte[]){
            return getBytes().length;
        }else if(data instanceof short[]){
            return getShorts().length;
        }else if(data instanceof int[]){
            return getInts().length;
        }else if(data instanceof long[]){
            return getLongs().length;
        }else if(data instanceof char[]){
            return getChars().length;
        }else if(data instanceof float[]){
            return getFloats().length;
        }else if(data instanceof double[]){
            return getDoubles().length;
        }else if(data instanceof RObject[]){
            return getRefs().length;
        }else{
            throw new IllegalArgumentException("不可能出现的情况");
        }
    }

    public byte[] getBytes(){
        return (byte[])data;
    }
    public short[] getShorts(){
        return (short[])data;
    }
    public int[] getInts(){
        return (int[])data;
    }
    public long[] getLongs(){
        return (long[])data;
    }
    public char[] getChars(){
        return (char[])data;
    }
    public float[] getFloats(){
        return (float[])data;
    }
    public double[] getDoubles(){
        return (double[])data;
    }
    public RObject[] getRefs(){
        return (RObject[])data;
    }

}
