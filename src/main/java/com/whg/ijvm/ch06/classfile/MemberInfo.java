package com.whg.ijvm.ch06.classfile;

import com.whg.ijvm.ch06.classfile.attribute.AttributeInfo;
import com.whg.ijvm.ch06.classfile.attribute.impl.CodeAttribute;
import com.whg.ijvm.ch06.classfile.attribute.impl.ConstantValueAttribute;
import com.whg.ijvm.ch06.classfile.uint.Uint16;

public class MemberInfo {

	private final ConstantPool constantPool;
	private final Uint16 accessFlags;
	private final Uint16 nameIndex;
	private final Uint16 descriptorIndex;
	private final AttributeInfo[] attributes;
	
	static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool){
		int memberCount = reader.readUint16().value();
		MemberInfo[] members = new MemberInfo[memberCount];
		for(int i=0;i<memberCount;i++){
			members[i] = readMember(reader, constantPool);
		}
		return members;
	}
	
	private static MemberInfo readMember(ClassReader reader, ConstantPool constantPool){
		return new MemberInfo(
			constantPool, 
			reader.readUint16(), 
			reader.readUint16(), 
			reader.readUint16(),
			ClassFile.readAttributes(reader, constantPool)
		);
	}
	
	public MemberInfo(ConstantPool constantPool, Uint16 accessFlags, Uint16 nameIndex, Uint16 descriptorIndex,
			AttributeInfo[] attributes) {
		this.constantPool = constantPool;
		this.accessFlags = accessFlags;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributes = attributes;
	}

	public Uint16 getAccessFlags() {
		return accessFlags;
	}

	public String getName(){
		return constantPool.getUtf8(nameIndex);
	}
	
	public String getDescriptor(){
		return constantPool.getUtf8(descriptorIndex);
	}

    public CodeAttribute getCodeAttribute() {
		for(AttributeInfo attrInfo: attributes){
			if(attrInfo instanceof CodeAttribute){
				return (CodeAttribute)attrInfo;
			}
		}
		return null;
    }

	public ConstantValueAttribute getConstantValueAttribute() {
		for(AttributeInfo attrInfo: attributes){
			if(attrInfo instanceof ConstantValueAttribute){
				return (ConstantValueAttribute)attrInfo;
			}
		}
		return null;
	}

}
