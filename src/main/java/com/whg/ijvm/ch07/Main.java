package com.whg.ijvm.ch07;

import java.util.Arrays;

import com.beust.jcommander.JCommander;

public class Main {

	//java -jar ijvm-jar-with-dependencies.jar -Xjre "C:\Program Files\Java\jdk1.8.0_51\jre" java.lang.String
	//java -jar ijvm-jar-with-dependencies.jar -cp D:\gitRepo\ijvm\target\classes com\whg\ijvm\ch07\test\FibonacciTest
	//java -jar ijvm-jar-with-dependencies.jar -verbose -verbose:inst -cp D:\gitRepo\ijvm\target\classes com\whg\ijvm\ch07\test\FibonacciTest 1 2 3
	//java -jar ijvm-jar-with-dependencies.jar -Xjre "/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre" java.lang.String
	//java -jar ijvm-jar-with-dependencies.jar -cp /Users/hoolai/whg/gitRepo/ijvm/target/classes com/whg/ijvm/ch07/GaussTest
	public static void main(String[] args) {
		JvmCmder jvmCmder = new JvmCmder();
		JCommander jCommander = JCommander.newBuilder().addObject(jvmCmder).build();

		int cpOrXjreIndex = findCpOrXjreIndex(args);
		// 能被jCommander解析的
		String[] cmdArgs = cpOrXjreIndex >= 0
				? Arrays.copyOfRange(args, 0, Math.min(args.length, cpOrXjreIndex+2))
				: args;
		// 自定义解析的类和后面传递的参数
		String[] classArgs = cpOrXjreIndex >= 0
				? Arrays.copyOfRange(args, cpOrXjreIndex+2, args.length)
				: new String[0];

		jCommander.parse(cmdArgs);
		jvmCmder.run(jCommander, classArgs);
	}

	private static int findCpOrXjreIndex(String[] args){
		for(int i=0;i<args.length;i++){
			String arg = args[i];
			if(arg.equals("-classpath") || arg.equals("-cp")
					|| arg.equals("-Xjre")){
				return i;
			}
		}
		return -1;
	}

}
