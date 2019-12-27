package com.whg.ijvm.ch02.classpath;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class DirEntry implements Entry {
	
	private final String path;
	
	DirEntry(String path){
		this.path = path;
	}

	@Override
	public byte[] readClass(String className) throws IOException {
		String fullName = this.path+File.separator;
		return IOUtils.resourceToByteArray(fullName);
	}

}
