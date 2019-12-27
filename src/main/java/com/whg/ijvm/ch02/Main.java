package com.whg.ijvm.ch02;

import java.util.Arrays;

import com.beust.jcommander.JCommander;

public class Main {

	//java -jar ijvm-jar-with-dependencies.jar -cp foo/bar MyApp arg1 arg2
	public static void main(String[] args) {
		JvmCmder jvmCmder = new JvmCmder();
		JCommander jCommander = JCommander.newBuilder().addObject(jvmCmder).build();
		jCommander.parse(args.length > 2 ? Arrays.copyOfRange(args, 0, 2) : args);
		jvmCmder.run(jCommander, args);
	}

}
