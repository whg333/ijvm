package com.whg.ijvm.ch03.classfile;

import com.whg.ijvm.ch03.Pair;
import com.whg.ijvm.ch03.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class ConstantPool {
	
	private ConstantInfo[] infos;
	
	static ConstantPool readConstantPool(ClassReader reader){
		int cpCount = reader.readUint16().value();

		return null;
	}

	public ConstantPool(ClassReader reader){
		int cpCount = reader.readUint16().value();
		infos = new ConstantInfo[cpCount];
		for(int i=1;i<cpCount;i++){
			infos[i] = readConstantInfo(reader);
//			if(infos[i].type == ConstantLongInfo
//				|| infos[i].type == ConstantDoubleInfo){
//				i++;
//			}
		}
	}

	private ConstantInfo readConstantInfo(ClassReader reader){
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
