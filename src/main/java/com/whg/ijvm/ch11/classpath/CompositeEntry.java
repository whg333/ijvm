package com.whg.ijvm.ch11.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CompositeEntry implements Entry {
	
	private final String pathList;
	private final List<Entry> entries;
	
	CompositeEntry(String pathList){
		this.pathList = pathList;
		entries = new ArrayList<>();
		String[] pathArr = StringUtils.split(pathList, File.pathSeparator);
		for (String path : pathArr) {
			entries.add(Entry.newEntry(path));
		}
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
		return pathList;
	}

}
