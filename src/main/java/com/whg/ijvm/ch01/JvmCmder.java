package com.whg.ijvm.ch01;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class JvmCmder {

	@Parameter(names = { "-help", "-?" }, description = "print help message")
	private boolean helpFlag;

	@Parameter(names = "-version", description = "print version and exit")
	private boolean versionFlag;

	@Parameter(names = { "-classpath", "-cp" }, description = "classpath")
	private String cpOption = "";

	private String clazz;
	private String[] args;

	public void run(JCommander jCommander, String[] args) {
		if (args.length > 2) {
			args = Arrays.copyOfRange(args, 2, args.length);
			this.clazz = args[0];
			this.args = Arrays.copyOfRange(args, 1, args.length);
		}

		if (versionFlag) {
			JCommander.getConsole().println("version 0.0.1");
		} else if (helpFlag || StringUtils.isEmpty(cpOption)) {
			jCommander.setProgramName("java -jar ijvm-jar-with-dependencies.jar");
			jCommander.usage();
		} else {
			startJVM();
		}
	}
	
	private void startJVM(){
		String info = String.format("classpath:%s class:%s args:%s", 
				cpOption, clazz, Arrays.toString(args));
		JCommander.getConsole().println(info);
	}

}
