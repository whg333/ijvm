package com.whg.ijvm.ch02.classpath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

public class Classpath {

	private Entry bootClasspath;
	private Entry extClasspath;
	private Entry userClasspath;
	
	public void parse(String jreOption, String cpOption){
		parseBootAndExtClasspath(jreOption);
		parseUserClasspath(cpOption);
	}
	
	private void parseBootAndExtClasspath(String jreOption){
		String jreDir = getJreDir(jreOption);
		
		// jre/lib/*
		String jreLibPath = FilePath.join(jreDir, "lib", "*");
		bootClasspath = Entry.newEntry(jreLibPath);
		
		// jre/lib/ext/*
		String jreExtPath = FilePath.join(jreDir, "lib", "ext", "*");
		extClasspath = Entry.newEntry(jreExtPath);
	}
	
	private String getJreDir(String jreOption){
		if(!StringUtils.isEmpty(jreOption) && FilePath.exists(jreOption)){
			return jreOption;
		}
		if(FilePath.exists("./jre")){
			return "./jre";
		}
		String javaHome = System.getProperty("JAVA_HOME");
		if(!StringUtils.isEmpty(javaHome)){
			return FilePath.join(javaHome, "jre");
		}
		javaHome = System.getProperty("java.home");
		if(!StringUtils.isEmpty(javaHome)){
			return FilePath.join(javaHome, "jre");
		}
		throw new IllegalArgumentException("Can not find jre folder!");
	}
	
	private void parseUserClasspath(String cpOption){
		if(StringUtils.isEmpty(cpOption)){
			// cpOption = ".";
			// cpOption = this.getClass().getClassLoader().getResource("").getPath();
			cpOption = Paths.get("").toAbsolutePath().toString()+File.separator;
		}
		userClasspath = Entry.newEntry(cpOption);
	}
	
	public byte[] readClass(String className) {
		className = className + ".class";
		byte[] bytes = bootClasspath.readClass(className);
		if(bytes != null){
			return bytes;
		}
		bytes = extClasspath.readClass(className);
		if(bytes != null){
			return bytes;
		}
		return userClasspath.readClass(className);
	}
	
	@Override
	public String toString() {
		return userClasspath.toString();
	}
	
}
