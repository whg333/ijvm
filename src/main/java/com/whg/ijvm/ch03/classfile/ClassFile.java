package com.whg.ijvm.ch03.classfile;

import com.whg.ijvm.ch03.classfile.uint.Uint16;
import com.whg.ijvm.ch03.classfile.uint.Uint32;

public class ClassFile {

	private Uint32 magic;
	private Uint16 minorVersion;
	private Uint16 majorVersion;
	private ConstantPool constantPool;
	private Uint16 accessFlags;
	private Uint16 thisClass;
	private Uint16 superClass;
	private Uint16[] interfaces;
	private MemberInfo[] fields;
	private MemberInfo[] methods;
	private AttributeInfo[] attributes;
	
	public static void parse(byte[] classBytes){
		ClassReader cr = new ClassReader(classBytes);
		ClassFile cf = new ClassFile();
		cf.read(cr);
	}
	
	private void read(ClassReader reader){
		readAndCheckMagic(reader);
		readAndCheckVersion(reader);
		constantPool = readConstantPool(reader);
		accessFlags = reader.readUint16();
		thisClass = reader.readUint16();
		superClass = reader.readUint16();
		interfaces = reader.readUint16s();
		fields = readMembers(reader, constantPool);
		methods = readMembers(reader, constantPool);
		attributes = readAttributes(reader, constantPool);
	}
	
	private void readAndCheckMagic(ClassReader reader){
		magic = reader.readUint32();
		if(magic.value() != Integer.toUnsignedLong(0xCAFEBABE)){
			throw new ClassFormatError("magic error!");
		}
	}
	
	private void readAndCheckVersion(ClassReader reader){
		
	}
	
	private ConstantPool readConstantPool(ClassReader reader){
		return new ConstantPool();
	}
	
	private MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool){
		return new MemberInfo[0];
	}
	
	private AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool){
		return new AttributeInfo[0];
	}
	
	public String getClassName(){
		return constantPool.getClassName(thisClass);
	}
	public String getSuperClassName(){
		if(superClass.value() > 0){
			return constantPool.getClassName(superClass);
		}
		return "";
	}
	public String[] getInterfaceNames(){
		String[] interfaceNames = new String[interfaces.length];
		for(int i=0;i<interfaceNames.length;i++){
			interfaceNames[i] = constantPool.getClassName(interfaces[i]);
		}
		return interfaceNames;
	}
}
