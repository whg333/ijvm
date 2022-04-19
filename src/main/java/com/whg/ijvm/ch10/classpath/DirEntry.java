package com.whg.ijvm.ch10.classpath;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class DirEntry implements Entry {
	
	private final String absDir;
	
	DirEntry(String absDir){
		this.absDir = absDir;
	}

	@Override
	public byte[] readClass(String className) {
		// System.out.println(Thread.currentThread().getContextClassLoader().getResource(".").getPath());
		// String fullName = absDir + File.separator +className;
		// String fullName = StringUtils.joinWith(File.separator, absDir, className);
		String fullName = FilePath.join(absDir, className);
		try {
			// return IOUtils.resourceToByteArray(fullName);
			return FileUtils.readFileToByteArray(FileUtils.getFile(fullName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString() {
		return absDir;
	}

}
