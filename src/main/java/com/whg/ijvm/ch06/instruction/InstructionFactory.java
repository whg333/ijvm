package com.whg.ijvm.ch06.instruction;

import com.whg.ijvm.ch06.instruction.compare.IFIcmp;
import com.whg.ijvm.ch06.instruction.compare.IFcond;
import com.whg.ijvm.ch06.instruction.constant.Const;
import com.whg.ijvm.ch06.instruction.constant.Ipush;
import com.whg.ijvm.ch06.instruction.constant.Ldc;
import com.whg.ijvm.ch06.instruction.control.Goto;
import com.whg.ijvm.ch06.instruction.load.ALoad;
import com.whg.ijvm.ch06.instruction.load.ILoad;
import com.whg.ijvm.ch06.instruction.math.Add;
import com.whg.ijvm.ch06.instruction.math.Inc;
import com.whg.ijvm.ch06.instruction.reference.*;
import com.whg.ijvm.ch06.instruction.stack.Dup;
import com.whg.ijvm.ch06.instruction.store.AStore;
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

            case 0x12:
                return new Ldc.LDC();

            case 0x1b:
                return new ILoad.ILOAD_1();
            case 0x1c:
                return new ILoad.ILOAD_2();

            case 0x2a:
                return new ALoad.ALOAD_0();
            case 0x2b:
                return new ALoad.ALOAD_1();
            case 0x2c:
                return new ALoad.ALOAD_2();
            case 0x2d:
                return new ALoad.ALOAD_3();

            case 0x3c:
                return new IStore.ISTORE_1();
            case 0x3d:
                return new IStore.ISTORE_2();

            case 0x4b:
                return new AStore.ASTORE_0();
            case 0x4c:
                return new AStore.ASTORE_1();
            case 0x4d:
                return new AStore.ASTORE_2();
            case 0x4e:
                return new AStore.ASTORE_3();

            case 0x59:
                return new Dup.DUP();

            case 0x60:
                return new Add.IADD();

            case 0x84:
                return new Inc.IINC();

            case 0x99:
                return new IFcond.IFEQ();

            case 0xa3:
                return new IFIcmp.IF_ICMPGT();

            case 0xa7:
                return new Goto();

            //References
            case 0xb1:
                return new Return.RETURN();
            case 0xb2:
                return new Static.GET_STATIC();
            case 0xb3:
                return new Static.PUT_STATIC();
            case 0xb4:
                return new InstField.GET_FIELD();
            case 0xb5:
                return new InstField.PUT_FIELD();
            case 0xb6:
                return new Invoke.INVOKE_VIRTUAL();
            case 0xb7:
                return new Invoke.INVOKE_SPECIAL();

            case 0xbb:
                return new New.NEW();
            case 0xc0:
                return new Instance.CHECK_CAST();
            case 0xc1:
                return new Instance.INSTANCE_OF();


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
