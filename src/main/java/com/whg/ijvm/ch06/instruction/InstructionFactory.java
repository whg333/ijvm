package com.whg.ijvm.ch06.instruction;

import com.whg.ijvm.ch06.instruction.compare.IFIcmp;
import com.whg.ijvm.ch06.instruction.constant.Const;
import com.whg.ijvm.ch06.instruction.constant.Ipush;
import com.whg.ijvm.ch06.instruction.control.Goto;
import com.whg.ijvm.ch06.instruction.load.ILoad;
import com.whg.ijvm.ch06.instruction.math.Add;
import com.whg.ijvm.ch06.instruction.math.Inc;
import com.whg.ijvm.ch06.instruction.store.IStore;
import com.whg.ijvm.ch06.instruction.store.LStore;

public class InstructionFactory {

    public static Instruction newInstruction(short opcode){
        switch (opcode){
            case 0x03:
                return new Const.ICONST_0();
            case 0x04:
                return new Const.ICONST_1();
            case 0x10:
                return new Ipush.BIPUSH();
            case 0x1b:
                return new ILoad.ILOAD_1();
            case 0x1c:
                return new ILoad.ILOAD_2();

            case 0x3c:
                return new IStore.ISTORE_1();
            case 0x3d:
                return new IStore.ISTORE_2();

            case 0x60:
                return new Add.IADD();

            case 0x84:
                return new Inc.IINC();

            case 0xa3:
                return new IFIcmp.IF_ICMPGT();

            case 0xa7:
                return new Goto();

            default:
                throw new UnsupportedOperationException("Unsupported opcode="+toHexStr(opcode));
        }
    }

    public static void main(String[] args) {
        short s = 0x03;
        System.out.printf("0x%02x\n", s);
        System.out.printf("%s\n", toHexStr(s));
    }

    private static String toHexStr(short s){
        StringBuilder sb = new StringBuilder(4);
        sb.append("0x");
        String hex = Integer.toHexString(s).toLowerCase();
        if(hex.length() == 1){
            sb.append("0");
        }
        sb.append(hex);
        return sb.toString();
    }

}
