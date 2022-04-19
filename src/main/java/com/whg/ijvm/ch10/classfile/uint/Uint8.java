package com.whg.ijvm.ch10.classfile.uint;

public class Uint8 {

	private final byte[] buf;

	public Uint8(byte buf) {
		this(new byte[]{buf});
	}
	
	public Uint8(byte[] buf) {
		this.buf = buf;
	}
	
	public short value() {
		return value(buf[0]);
	}

	public static short value(byte b){
		int firstByte = (0x000000FF & ((int)b));
		short anUnsignedByte = (short)firstByte;
		return anUnsignedByte;
	}
	
}