package com.whg.ijvm.ch06.instruction.base;

import com.whg.ijvm.ch06.instruction.AbstractInstruction;

/**
 * 没有操作数的指令:
 *  即操作数（局部变量表索引）其实是隐含在操作码中，例如iload_0、iload_1、iload_2等等
 */
public abstract class NoOperandsInstruction extends AbstractInstruction {

}
