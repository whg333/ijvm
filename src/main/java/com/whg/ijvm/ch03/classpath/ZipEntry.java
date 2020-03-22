package com.whg.ijvm.ch03.classpath;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ZipEntry implements Entry {
	
	private final String absPath;
	
	ZipEntry(String absPath){
		this.absPath = absPath;
	}

	@Override
	public byte[] readClass(String className) {
		File file = FileUtils.getFile(absPath);
		try(ZipFile zipFile = new ZipFile(file)){
//			Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();
//			while(entries.hasMoreElements()){
//				java.util.zip.ZipEntry entry = entries.nextElement();
//				if(entry.getName().equals(className)){
//					System.out.println(entry.getName());
//					return entry.getExtra();
//				}
//			}
//			return null;
			Optional<? extends java.util.zip.ZipEntry> first = zipFile.stream()
					.filter(e -> e.getName().equals(className))
					.findFirst();
			if(first.isPresent()){
				java.util.zip.ZipEntry firstEntry = first.get();
//				return firstEntry.getExtra();
				InputStream input = zipFile.getInputStream(firstEntry);
				return IOUtils.toByteArray(input);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString() {
		return absPath;
	}

}
