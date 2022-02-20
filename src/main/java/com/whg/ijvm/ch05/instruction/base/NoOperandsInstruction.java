package com.whg.ijvm.ch05.instruction.base;

import com.whg.ijvm.ch05.instruction.Instruction;

/**
 * 没有操作数的指令:
 *  即操作数（局部变量表索引）其实是隐含在操作码中，例如iload_0、iload_1、iload_2等等
 */
public abstract class NoOperandsInstruction implements Instruction {
    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}