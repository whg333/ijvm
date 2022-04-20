package com.whg.ijvm.ch11.classfile.uint;

import java.math.BigInteger;

@Deprecated
public class Uint64 {

	private final byte[] data;
	
	public Uint64(byte[] data) {
		this.data = data;
	}

	public BigInteger value() {
		return new BigInteger(data);
	}

	public static void main(String[] args) {
		//0x7fffffffffffffff
		System.out.println(Long.MAX_VALUE);
		System.out.println(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.valueOf(1)));
		byte[] bytes = {
				(byte)0x7f,
				(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
				(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
		};
		System.out.println(new BigInteger(bytes));
	}
	
}