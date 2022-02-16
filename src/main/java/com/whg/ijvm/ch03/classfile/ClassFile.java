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
		constantPool = ConstantPool.readConstantPool(reader);
		accessFlags = reader.readUint16();
		thisClass = reader.readUint16();
		superClass = reader.readUint16();
		interfaces = reader.readUint16s();
		fields = MemberInfo.readMembers(reader, constantPool);
		methods = MemberInfo.readMembers(reader, constantPool);
		attributes = readAttributes(reader, constantPool);
	}
	
	private void readAndCheckMagic(ClassReader reader){
		magic = reader.readUint32();
		long magicVal = magic.value();
		String magicValHexStr = Long.toHexString(magicVal).toUpperCase();
		System.out.println("magic="+magicValHexStr);
		if(magicVal != Integer.toUnsignedLong(0xCAFEBABE)){
			throw new ClassFormatError("magic error!"+magicValHexStr);
		}
	}
	
	private void readAndCheckVersion(ClassReader reader){
		minorVersion = reader.readUint16();
		majorVersion = reader.readUint16();
		int majorVerVal = majorVersion.value();
		int minorVerVal = minorVersion.value();
		String version = majorVerVal+"."+minorVerVal;
		System.out.println("version="+version);
		if(majorVerVal == 45){
			return;
		}
		if(majorVerVal >= 46 && majorVerVal <= 52 
				&& minorVerVal == 0){
			return;
		}
		throw new UnsupportedClassVersionError("version error!"+version);
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
