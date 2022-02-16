package com.whg.ijvm.ch03.classfile;

import com.whg.ijvm.ch03.Pair;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class ConstantPool {
	
	private ConstantInfo[] infos;
	
	static ConstantPool readConstantPool(ClassReader reader){
		return null;
	}
	
	public ConstantInfo getConstantInfo(Uint16 index){
		return infos[index.value()];
	}
	
	public Pair<String> getNameAndType(Uint16 index){
		return Pair.of(null, null);
	}

	public String getClassName(Uint16 index) {
		return null;
	}

	public String getUtf8(Uint16 index) {
		return null;
	}

}
