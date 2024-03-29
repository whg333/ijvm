package com.whg.ijvm.ch08.instruction;

import com.whg.ijvm.ch08.instruction.compare.IFAcmp;
import com.whg.ijvm.ch08.instruction.compare.IFIcmp;
import com.whg.ijvm.ch08.instruction.compare.IFcond;
import com.whg.ijvm.ch08.instruction.compare.Lcmp;
import com.whg.ijvm.ch08.instruction.constant.Const;
import com.whg.ijvm.ch08.instruction.constant.Ipush;
import com.whg.ijvm.ch08.instruction.constant.Ldc;
import com.whg.ijvm.ch08.instruction.constant.Nop;
import com.whg.ijvm.ch08.instruction.control.Goto;
import com.whg.ijvm.ch08.instruction.control.Return;
import com.whg.ijvm.ch08.instruction.load.*;
import com.whg.ijvm.ch08.instruction.math.Add;
import com.whg.ijvm.ch08.instruction.math.Inc;
import com.whg.ijvm.ch08.instruction.math.Mul;
import com.whg.ijvm.ch08.instruction.math.Sub;
import com.whg.ijvm.ch08.instruction.reference.*;
import com.whg.ijvm.ch08.instruction.stack.Dup;
import com.whg.ijvm.ch08.instruction.stack.Pop;
import com.whg.ijvm.ch08.instruction.store.*;

public class InstructionFactory {

    // TODO 优化一些不需要操作数的类成单例
    public static Instruction newInstruction(short opcode){
        switch (opcode){
            case 0x00:
                return new Nop.NOP();
            case 0x01:
                return new Const.ACONST_NULL();
            case 0x02:
                return new Const.ICONST_M1();
            case 0x03:
                return new Const.ICONST_0();
            case 0x04:
                return new Const.ICONST_1();
            case 0x05:
                return new Const.ICONST_2();
            case 0x06:
                return new Const.ICONST_3();
            case 0x07:
                return new Const.ICONST_4();
            case 0x08:
                return new Const.ICONST_5();

            case 0x0a:
                return new Const.LCONST_1();
            case 0x10:
                return new Ipush.BIPUSH();

            case 0x12:
                return new Ldc.LDC();
            case 0x14:
                return new Ldc.LDC2_W();
            case 0x15:
                return new ILoad.ILOAD();
            case 0x18:
                return new DLoad.DLOAD();
            case 0x19:
                return new ALoad.ALOAD();

            case 0x1a:
                return new ILoad.ILOAD_0();
            case 0x1b:
                return new ILoad.ILOAD_1();
            case 0x1c:
                return new ILoad.ILOAD_2();
            case 0x1d:
                return new ILoad.ILOAD_3();
            case 0x1e:
                return new LLoad.LLOAD_0();
            case 0x1f:
                return new LLoad.LLOAD_1();
            case 0x20:
                return new LLoad.LLOAD_2();
            case 0x21:
                return new LLoad.LLOAD_3();
            case 0x24:
                return new FLoad.FLOAD_2();

            case 0x26:
                return new DLoad.DLOAD_0();
            case 0x27:
                return new DLoad.DLOAD_1();
            case 0x28:
                return new DLoad.DLOAD_2();
            case 0x29:
                return new DLoad.DLOAD_3();

            case 0x2a:
                return new ALoad.ALOAD_0();
            case 0x2b:
                return new ALoad.ALOAD_1();
            case 0x2c:
                return new ALoad.ALOAD_2();
            case 0x2d:
                return new ALoad.ALOAD_3();

            case 0x2e:
                return new ArrLoad.IALOAD();

            case 0x32:
                return new ArrLoad.AALOAD();
            case 0x3a:
                return new AStore.ASTORE();
            case 0x36:
                return new IStore.ISTORE();
            case 0x3c:
                return new IStore.ISTORE_1();
            case 0x3d:
                return new IStore.ISTORE_2();
            case 0x3e:
                return new IStore.ISTORE_3();

            case 0x40:
                return new LStore.LSTORE_1();
            case 0x45:
                return new FStore.FSTORE_2();

            case 0x4b:
                return new AStore.ASTORE_0();
            case 0x4c:
                return new AStore.ASTORE_1();
            case 0x4d:
                return new AStore.ASTORE_2();
            case 0x4e:
                return new AStore.ASTORE_3();

            case 0x4f:
                return new ArrStore.IASTORE();

            case 0x57:
                return new Pop.POP();
            case 0x59:
                return new Dup.DUP();

            case 0x5a:
                return new Dup.DUP_X1();

            case 0x60:
                return new Add.IADD();
            case 0x61:
                return new Add.LADD();
            case 0x62:
                return new Add.FADD();
            case 0x63:
                return new Add.DADD();
            case 0x64:
                return new Sub.ISUB();
            case 0x65:
                return new Sub.LSUB();
            case 0x66:
                return new Sub.FSUB();
            case 0x67:
                return new Sub.DSUB();

            case 0x68:
                return new Mul.IMUL();
            case 0x69:
                return new Mul.LMUL();
            case 0x6a:
                return new Mul.FMUL();
            case 0x6b:
                return new Mul.DMUL();

            case 0x84:
                return new Inc.IINC();

            case 0x94:
                return new Lcmp.LCMP();
            case 0x99:
                return new IFcond.IFEQ();
            case 0x9d:
                return new IFcond.IFGT();

            case 0xa3:
                return new IFIcmp.IF_ICMPGT();
            case 0xa2:
                return new IFIcmp.IF_ICMPGE();
            case 0xa4:
                return new IFIcmp.IF_ICMPLE();

            case 0xa6:
                return new IFAcmp.IF_ACMPNE();
            case 0xa7:
                return new Goto();

            case 0xac:
                return new Return.IRETURN();
            case 0xad:
                return new Return.LRETURN();
            case 0xae:
                return new Return.FRETURN();
            case 0xaf:
                return new Return.DRETURN();
            case 0xb0:
                return new Return.ARETURN();
            case 0xb1:
                return new Return.RETURN();

            //References
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
            case 0xb8:
                return new Invoke.INVOKE_STATIC();
            case 0xb9:
                return new Invoke.INVOKE_INTERFACE();

            case 0xbb:
                return new New.NEW();
            case 0xbc:
                return new Arr.NEW_ARRAY();
            case 0xbd:
                return new Arr.ANEW_ARRAY();
            case 0xbe:
                return new Arr.ARRAY_LENGTH();

            case 0xc0:
                return new Instance.CHECK_CAST();
            case 0xc1:
                return new Instance.INSTANCE_OF();

            case 0xc5:
                return new Arr.MULTI_ANEW_ARRAY();

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
