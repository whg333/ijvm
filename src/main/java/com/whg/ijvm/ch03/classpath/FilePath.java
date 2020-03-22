package com.whg.ijvm.ch03.classpath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

public class FilePath {

	public static String join(String... paths){
		return StringUtils.joinWith(File.separator, paths);
	}
	
	public static String joinPath(String... paths){
		return StringUtils.joinWith(File.pathSeparator, paths);
	}
	
	public static boolean exists(String path){
		// File file = new File(path);
		// return file.exists();
		return Files.exists(Paths.get(path));
	}
	
}
