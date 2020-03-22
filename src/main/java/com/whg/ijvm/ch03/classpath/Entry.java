package com.whg.ijvm.ch03.classpath;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;

public interface Entry {

	byte[] readClass(String className);
	
	static Entry newEntry(String path){
		if(path.contains(File.pathSeparator)){
			return new CompositeEntry(path);
		}
		if(path.contains("\\*")){
			return new WildcardEntry(path);
		}
		if(path.contains(".jar") || path.contains(".JAR") || 
				path.contains(".zip") || path.contains(".ZIP")){
			return new ZipEntry(path);
		}
		return new DirEntry(path);
	}
	
}
