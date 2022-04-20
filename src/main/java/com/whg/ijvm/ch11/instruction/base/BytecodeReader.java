package com.whg.ijvm.ch11.instruction.base;

import com.whg.ijvm.ch11.classfile.uint.Uint16;
import com.whg.ijvm.ch11.classfile.uint.Uint8;

public class BytecodeReader {

    byte[] code;
    int pc;

    public Uint8 readUint8(){
        Uint8 i = new Uint8(code[pc]);
        pc++;
        return i;
    }

    public byte readInt8(){
        return (byte)readUint8().value();
    }

    public Uint16 readUint16(){
        short high = readUint8().value();
        short low = readUint8().value();
        //high << 8 | low
        return new Uint16(new byte[]{(byte) high, (byte) low});
    }

    public short readInt16(){
        return (short)readUint16().value();
    }

    public int readInt32(){
        short firstByte = readUint8().value();
        short secondByte = readUint8().value();
        short thirdByte = readUint8().value();
        short fourthByte = readUint8().value();
        return firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte;
    }

    public void skipPadding() {
        while (pc % 4 != 0){
            readUint8();
        }
    }

    public int[] readInt32s(int length) {
        int[] ints = new int[length];
        for(int i=0;i<length;i++){
            ints[i] = readInt32();
        }
        return ints;
    }

    public void reset(byte[] code, int pc){
        this.code = code;
        this.pc = pc;
    }

    public int getPc() {
        return pc;
    }

}
