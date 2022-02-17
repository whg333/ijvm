package com.whg.ijvm.ch03.classfile;

import com.whg.ijvm.ch03.Pair;
import com.whg.ijvm.ch03.classfile.constantinfo.ConstantClassInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.NameAndTypeInfo;
import com.whg.ijvm.ch03.classfile.constantinfo.string.ConstantUtf8Info;
import com.whg.ijvm.ch03.classfile.uint.Uint16;

public class ConstantPool {
	
	private ConstantInfo[] infos;
	
	static ConstantPool readConstantPool(ClassReader reader){
		return new ConstantPool(reader);
	}

	private ConstantPool(ClassReader reader){
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
	
	public <T extends ConstantInfo> T getConstantInfo(Uint16 index){
		return (T)(infos[index.value()]);
	}
	
	public Pair<String, String> getNameAndType(Uint16 index){
		NameAndTypeInfo info = getConstantInfo(index);
		return Pair.of(getUtf8(info.nameIndex), getUtf8(info.descriptorIndex));
	}

	public String getClassName(Uint16 index) {
		ConstantClassInfo info = getConstantInfo(index);
		return getUtf8(info.nameIndex);
	}

	public String getUtf8(Uint16 index) {
		ConstantUtf8Info info = getConstantInfo(index);
		return info.str;
	}

}
