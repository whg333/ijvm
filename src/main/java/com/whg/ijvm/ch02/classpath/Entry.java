package com.whg.ijvm.ch02.classpath;

import java.io.File;
import java.io.IOException;

public interface Entry {

	byte[] readClass(String className) throws IOException;
	
	default Entry newEntry(String path){
		if(path.contains(File.separator)){
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
