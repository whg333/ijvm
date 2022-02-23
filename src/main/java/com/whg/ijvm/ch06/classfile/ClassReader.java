package com.whg.ijvm.ch06.classfile;

import java.util.LinkedList;

import com.whg.ijvm.ch06.classfile.uint.Uint16;
import com.whg.ijvm.ch06.classfile.uint.Uint32;
import com.whg.ijvm.ch06.classfile.uint.Uint64;
import com.whg.ijvm.ch06.classfile.uint.Uint8;

public class ClassReader {

	private final LinkedList<Byte> bytes;
	
	public ClassReader(byte[] data) {
		bytes = new LinkedList<>();
		for(byte b: data){
			bytes.add(b);
		}
	}

	public Uint8 readUint8(){
		return new Uint8(readBytes(1));
	}
	
	public Uint16 readUint16(){
		return new Uint16(readBytes(2));
	}
	
	public Uint32 readUint32(){
		return new Uint32(readBytes(4));
	}

	@Deprecated
	public Uint64 readUint64(){
		return new Uint64(readBytes(8));
	}
	
	public Uint16[] readUint16s(){
		Uint16 uint16 = readUint16();
		int uint16Val = uint16.value();
		Uint16[] result = new Uint16[uint16Val];
		for(int i=0;i<uint16Val;i++){
			result[i] = readUint16();
		}
		return result;
	}
	
	public byte[] readBytes(int length){
		byte[] result = new byte[length];
		for(int i=0;i<length;i++){
			result[i] = bytes.removeFirst();
		}
		return result;
	}
	
}
