package com.whg.ijvm.ch09.test;

import com.whg.ijvm.ch09.runtime.LocalVars;
import com.whg.ijvm.ch09.runtime.OperandStack;
import com.whg.ijvm.ch09.runtime.RFrame;

public class RunTimeTest {

	public static void main(String[] args) throws RuntimeException{
		// System.out.println(circumference(1.6f));
		RFrame frame = new RFrame(100, 100);
		testLocalVars(frame.getLocalVars());
		System.out.println("--------");
		testOperandStack(frame.getOperandStack());
	}

	private static float circumference(float r){
		float pi = 3.14f;
		float area = 2 * pi * r;
		return area;
	}

	private static void testLocalVars(LocalVars vars){
		vars.setInt(0, 100);
		vars.setInt(1, -100);
		vars.setLong(2, 2997924580123456789L);
		vars.setLong(4, -2997924580123456789L);
		vars.setFloat(6, 3.1415926f);
		vars.setDouble(7, -2.71828182845);
		vars.setRef(9, null);

		System.out.println(vars.getInt(0));
		System.out.println(vars.getInt(1));
		System.out.println(vars.getLong(2));
		System.out.println(vars.getLong(4));
		System.out.println(vars.getFloat(6));
		System.out.println(vars.getDouble(7));
		System.out.println(vars.getRef(9));
	}

	private static void testOperandStack(OperandStack stack){
		stack.pushInt(100);
		stack.pushInt(-100);
		stack.pushLong(2997924580123456789L);
		stack.pushLong(-2997924580123456789L);
		stack.pushFloat(3.1415926f);
		stack.pushDouble(-2.71828182845);
		stack.pushRef(null);

		System.out.println(stack.popRef());
		System.out.println(stack.popDouble());
		System.out.println(stack.popFloat());
		System.out.println(stack.popLong());
		System.out.println(stack.popLong());
		System.out.println(stack.popInt());
		System.out.println(stack.popInt());
	}
	
}
