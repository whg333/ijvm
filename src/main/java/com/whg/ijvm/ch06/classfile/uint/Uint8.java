package com.whg.ijvm.ch06.classfile.uint;

public class Uint8 {

	private final byte[] buf;

	public Uint8(byte buf) {
		this(new byte[]{buf});
	}
	
	public Uint8(byte[] buf) {
		this.buf = buf;
	}
	
	public short value() {
		int firstByte = (0x000000FF & ((int)buf[0]));
		short anUnsignedByte = (short)firstByte;
		return anUnsignedByte;
	}
	
}