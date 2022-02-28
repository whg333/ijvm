package com.whg.ijvm.ch06.classfile;

import com.whg.ijvm.ch06.classfile.constantinfo.ConstantInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.ConstantInfoFactory;
import com.whg.ijvm.ch06.classfile.constantinfo.member.ClassInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.member.NameAndTypeInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantDoubleInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.numeric.ConstantLongInfo;
import com.whg.ijvm.ch06.classfile.constantinfo.string.ConstantUtf8Info;
import com.whg.ijvm.ch06.classfile.uint.Uint16;
import org.apache.commons.lang3.tuple.Pair;

public class ConstantPool {
	
	private final ConstantInfo[] infos;
	
	static ConstantPool readConstantPool(ClassReader reader){
		return new ConstantPool(reader);
	}

	private ConstantPool(ClassReader reader){
		int cpCount = reader.readUint16().value();
		infos = new ConstantInfo[cpCount];
		for(int i=1;i<cpCount;i++){
			infos[i] = readConstantInfo(reader);
			if(infos[i] instanceof ConstantLongInfo
				|| infos[i] instanceof ConstantDoubleInfo){
				i++;
			}
		}
	}

	private ConstantInfo readConstantInfo(ClassReader reader){
		return ConstantInfoFactory.readConstantInfo(reader, this);
	}
	
	public Pair<String, String> getNameAndType(Uint16 index){
		NameAndTypeInfo info = getConstantInfo(index);
		return info.getNameAndType();
	}

	public String getClassName(Uint16 index) {
		ClassInfo info = getConstantInfo(index);
		return info.getName();
	}

	public String getUtf8(Uint16 index) {
		ConstantUtf8Info info = getConstantInfo(index);
		return info.str;
	}

	public <T extends ConstantInfo> T getConstantInfo(Uint16 index){
		return (T) infos[index.value()];
	}

	public ConstantInfo[] getInfos(){
		return infos;
	}

}
