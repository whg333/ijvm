package com.whg.ijvm.ch05.classfile;

import com.whg.ijvm.ch05.classfile.attribute.AttributeInfo;
import com.whg.ijvm.ch05.classfile.attribute.AttributeInfoFactory;
import com.whg.ijvm.ch05.classfile.uint.Uint16;
import com.whg.ijvm.ch05.classfile.uint.Uint32;

import java.util.Arrays;

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
	
	public static ClassFile parse(byte[] classBytes){
		ClassReader cr = new ClassReader(classBytes);
		ClassFile cf = new ClassFile();
		cf.read(cr);
		return cf;
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
		// System.out.println("magic="+magicValHexStr);
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
		// System.out.println("version="+version);
		if(majorVerVal == 45){
			return;
		}
		if(majorVerVal >= 46 && majorVerVal <= 52 
				&& minorVerVal == 0){
			return;
		}
		throw new UnsupportedClassVersionError("version error!"+version);
	}
	
	public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool){
		int attrCount = reader.readUint16().value();
		AttributeInfo[] attributeInfos = new AttributeInfo[attrCount];
		for(int i=0;i<attrCount;i++){
			attributeInfos[i] = readAttribute(reader, constantPool);
		}
		return attributeInfos;
	}

	private static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) {
		Uint16 attrNameIndex = reader.readUint16();
		String attrName = cp.getUtf8(attrNameIndex);
		int attrLen = (int) reader.readUint32().value();
		AttributeInfo attrInfo = AttributeInfoFactory.newAttributeInfo(attrName, attrLen, cp);
		attrInfo.readInfo(reader);
		return attrInfo;
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

	public MemberInfo getMainMethod() {
		for(MemberInfo method: methods){
			if(method.getName().equals("main")
					&& method.getDescriptor().equals("([Ljava/lang/String;)V")){
				return method;
			}
		}
		return null;
	}

	public void printInfo() {
		StringBuilder sb = new StringBuilder("\n");
		sb.append(String.format("minor version: %d\n", minorVersion.value()));
		sb.append(String.format("major version: %d\n", majorVersion.value()));
		sb.append(String.format("flags: (%s)\n", "0x"+Integer.toHexString(accessFlags.value())));
		sb.append(String.format("this_class : %s\n", getClassName()));
		sb.append(String.format("super_class : %s\n", getSuperClassName()));
		sb.append(String.format("interfaces : %s\n", Arrays.toString(getInterfaceNames())));

		sb.append(String.format("constants count : %d\n", constantPool.getLength()));

		sb.append(String.format("fields count : %d\n", fields.length));
		for(MemberInfo field: fields){
			sb.append(String.format("\t%s\n", field.getName()));
		}

		sb.append(String.format("methods count : %d\n", methods.length));
		for(MemberInfo method: methods){
			sb.append(String.format("\t%s\n", method.getName()));
		}

		sb.append(String.format("attributes count : %d\n", attributes.length));
		for(AttributeInfo attribute: attributes){
			sb.append(String.format("\t%s\n", attribute.getName()));
		}

		System.out.println(sb);
	}

}
