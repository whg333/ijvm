package com.whg.ijvm.ch07;

import java.io.File;
import java.util.Arrays;

import com.whg.ijvm.ch07.classfile.MemberInfo;
import com.whg.ijvm.ch07.classpath.ClassData;
import com.whg.ijvm.ch07.heap.RClass;
import com.whg.ijvm.ch07.heap.RClassLoader;
import com.whg.ijvm.ch07.heap.RMethod;
import com.whg.ijvm.ch07.instruction.Interpreter;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Console;
import com.whg.ijvm.ch07.classpath.Classpath;
import com.whg.ijvm.ch07.classfile.ClassFile;

public class JvmCmder {

	@Parameter(names = { "-help", "-?" }, description = "print help message")
	private boolean helpFlag;

	@Parameter(names = "-version", description = "print version and exit")
	private boolean versionFlag;

	@Parameter(names = { "-verbose:class", "-verbose" }, description = "enable verbose output")
	private boolean verboseClassFlag;

	@Parameter(names = "-verbose:inst", description = "enable verbose output")
	private boolean verboseInstFlag;

	@Parameter(names = { "-classpath", "-cp" }, description = "classpath")
	private String cpOption;
	
	@Parameter(names = "-Xjre", description = "path to jre")
	private String jreOption;

	private String clazz;
	private String[] args;

	public void run(JCommander jCommander, String[] classArgs) {
		if (classArgs.length > 0) {
			clazz = classArgs[0];
			if(classArgs.length > 1){
				args = Arrays.copyOfRange(classArgs, 1, classArgs.length);
			}
		}

		if (versionFlag) {
			JCommander.getConsole().println("version 0.0.1");
		} else if (helpFlag || 
				(StringUtils.isEmpty(jreOption) && StringUtils.isEmpty(cpOption))) {
			jCommander.setProgramName("java -jar ijvm-jar-with-dependencies.jar");
			jCommander.usage();
		} else {
			startJVM();
		}
	}
	
	private void startJVM(){
		Classpath cp = new Classpath();
		cp.parse(jreOption, cpOption);
		
		Console console = JCommander.getConsole();
		console.println(String.format("classpath:%s\nclass:[%s]\nargs:%s", cp, clazz, Arrays.toString(args)));
		
		clazz = clazz.replaceAll("\\.", "/");
		// clazz = RegExUtils.replaceAll(clazz, "/.", "////");
		ClassData classData = cp.readClass(clazz);
		if(classData == null){
			console.println(String.format("Can not found class:[%s]", clazz));
			return;
		}

		byte[] bytes = classData.bytes;
		String[] unsignedBytes = unsignedBytes(bytes);
		console.println(String.format("class data:%s", Arrays.toString(unsignedBytes)));

		ClassFile classFile = ClassFile.parse(bytes);
		console.println(classFile.getPrintInfo());

		RClassLoader classLoader = new RClassLoader(cp, verboseClassFlag);
		RClass mainClass = classLoader.loadClass(clazz);
		RMethod mainMethod = mainClass.getMainMethod();
		// MemberInfo mainMethod = classFile.getMainMethod();
		if(mainMethod == null){
			console.println(String.format("Main method not found in class:[%s]", clazz));
		}else{
			Interpreter.run(mainMethod, verboseInstFlag);
		}
	}
	
	private String[] unsignedBytes(byte[] bytes){
		String[] uints = new String[bytes.length];
		for(int i=0;i<bytes.length;i++){
			String hex = Integer.toHexString(Byte.toUnsignedInt(bytes[i])).toUpperCase();
			uints[i] = hex.length() == 1 ? "0"+hex : hex;
		}
		return uints;
	}

}
