package com.whg.ijvm.ch03.classfile.uint;

public class Uint16 {

	private final byte[] buf;
	
	public Uint16(byte[] buf) {
		this.buf = buf;
	}

	public char value() {
		int firstByte = (0x000000FF & ((int)buf[0]));
		int secondByte = (0x000000FF & ((int)buf[1]));
		char anUnsignedShort = (char) (firstByte << 8 | secondByte); 
		return anUnsignedShort;
	}
	
}