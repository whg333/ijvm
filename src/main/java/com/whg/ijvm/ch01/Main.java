package com.whg.ijvm.ch01;

import java.util.Arrays;

import com.beust.jcommander.JCommander;

public class Main {

	public static void main(String[] args) {
		JvmCmder cmder = new JvmCmder();
		JCommander jCommander = JCommander.newBuilder().addObject(cmder).build();
		jCommander.parse(args.length > 2 ? Arrays.copyOfRange(args, 0, 2) : args);
		cmder.run(jCommander, args);
	}

}
