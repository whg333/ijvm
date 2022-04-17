package com.whg.ijvm.ch09.heap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RArray extends RObject{

    private static final Map<String, String> primitiveTypes = new HashMap<String, String>(){
        {
            put("void", "V");
            put("boolean", "Z");
            put("byte", "B");
            put("short", "S");
            put("int", "I");
            put("long", "J");
            put("char", "C");
            put("float", "F");
            put("double", "D");
        }
    };

    RArray(RClass clazz, Object data){
        super(clazz, data);
    }

    RArray(RClass clazz, String name, int count){
        this.clazz = clazz;
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

    public static String getArrayClassName(String className){
        return "["+toDescriptor(className);
    }

    private static String toDescriptor(String className) {
        if(RClass.isArray(className)){
            return className;
        }
        String type = primitiveTypes.get(className);
        if(type != null){
            return type;
        }
        return "L" + className + ";";
    }

    public static void checkIndex(int arrLen, int index){
        if(index < 0 || index >= arrLen){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static String getComponentClassName(String className) {
        if(className.charAt(0) == '['){
            String componentTypeDescriptor = className.substring(1);
            return RArray.toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not array: "+className);
    }

    private static String toClassName(String descriptor) {
        if(descriptor.charAt(0) == '['){
            return descriptor;
        }
        if(descriptor.charAt(0) == 'L'){
            return descriptor.substring(1, descriptor.length()-1);
        }
        for(Map.Entry<String, String > entry: primitiveTypes.entrySet()){
            String className = entry.getKey();
            String d = entry.getValue();
            if(d.equals(descriptor)){
                return className;
            }
        }
        throw new RuntimeException("Invalid descriptor: "+descriptor);
    }

    public static Collection<String> primitiveTypeKeys(){
        return primitiveTypes.keySet();
    }

    public static boolean isPrimitive(String name){
        return primitiveTypes.containsKey(name);
    }

    public static void arrayCopy(RArray src, int srcPos, RArray dest, int destPos, int length){
        System.arraycopy(src.data, srcPos, dest.data, destPos, length);
    }

}
