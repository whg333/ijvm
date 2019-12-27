package com.whg.ijvm.ch02.classpath;

public class ZipEntry implements Entry {
	
	private final String path;
	
	ZipEntry(String path){
		this.path = path;
	}

	@Override
	public byte[] readClass(String className) {
		// TODO Auto-generated method stub
		return null;
	}

}
