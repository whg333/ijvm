package com.whg.ijvm.ch11.classfile.uint;

public class Uint32 {

	private final byte[] buf;
	
	public Uint32(byte[] buf) {
		this.buf = buf;
	}
	
	public long value() {
		int firstByte = (0x000000FF & ((int)buf[0])); 
		int secondByte = (0x000000FF & ((int)buf[1])); 
		int thirdByte = (0x000000FF & ((int)buf[2])); 
		int fourthByte = (0x000000FF & ((int)buf[3])); 
		long anUnsignedInt = (((long) firstByte << 24
		                        | secondByte << 16 
		                        | thirdByte << 8 
		                        | fourthByte)) 
		                        & 0xFFFFFFFFL;
		return anUnsignedInt;
	}
	
}