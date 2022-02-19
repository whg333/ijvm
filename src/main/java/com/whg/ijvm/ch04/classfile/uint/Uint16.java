package com.whg.ijvm.ch04.classfile.uint;

public class Uint16 {

	private final byte[] buf;
	
	public Uint16(byte[] buf) {
		this.buf = buf;
	}

	public int value() {
		int firstByte = (0x000000FF & ((int)buf[0]));
		int secondByte = (0x000000FF & ((int)buf[1]));
		//虽然可以使用char来代表无符号的short，但打印输出还是int直观，char是输出字符而不是数字
		int anUnsignedShort = (firstByte << 8 | secondByte); 
		return anUnsignedShort;
	}
	
}