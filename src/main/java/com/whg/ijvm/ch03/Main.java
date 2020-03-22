package com.whg.ijvm.ch03;

import java.util.Arrays;

import com.beust.jcommander.JCommander;

public class Main {

	//java -jar ijvm-jar-with-dependencies.jar -Xjre "C:\Program Files\Java\jdk1.8.0_51\jre" java.lang.Object
	public static void main(String[] args) {
		JvmCmder jvmCmder = new JvmCmder();
		JCommander jCommander = JCommander.newBuilder().addObject(jvmCmder).build();
		jCommander.parse(args.length > 2 ? Arrays.copyOfRange(args, 0, 2) : args);
		jvmCmder.run(jCommander, args);
	}

}
