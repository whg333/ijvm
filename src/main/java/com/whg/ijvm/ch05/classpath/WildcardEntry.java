package com.whg.ijvm.ch05.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class WildcardEntry implements Entry {
	
	private final String absDir;
	private final List<Entry> entries;
	
	WildcardEntry(String absDir){
		this.absDir = absDir;
		entries = new ArrayList<>();
		String basePath = absDir.substring(0, absDir.length()-1);
		File baseDir = FileUtils.getFile(basePath);
		Collection<File> files = FileUtils.listFiles(
				baseDir, new String[]{"jar", "JAR"}, false);
		files.forEach(f -> entries.add(Entry.newEntry(f.getAbsolutePath())));
	}

	@Override
	public byte[] readClass(String className) {
		for (Entry entry : entries) {
			byte[] bytes = entry.readClass(className);
			if(bytes != null){
				return bytes;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return absDir;
	}

}
