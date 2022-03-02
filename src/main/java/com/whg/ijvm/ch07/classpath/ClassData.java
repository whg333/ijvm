package com.whg.ijvm.ch07.classpath;

public class ClassData {

    public final byte[] bytes;
    public final Entry entry;

    public ClassData(byte[] bytes, Entry entry) {
        this.bytes = bytes;
        this.entry = entry;
    }

}
