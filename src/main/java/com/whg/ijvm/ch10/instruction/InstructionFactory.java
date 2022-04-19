package com.whg.ijvm.ch10.instruction;

import com.whg.ijvm.ch10.instruction.compare.*;
import com.whg.ijvm.ch10.instruction.constant.Const;
import com.whg.ijvm.ch10.instruction.constant.Ipush;
import com.whg.ijvm.ch10.instruction.constant.Ldc;
import com.whg.ijvm.ch10.instruction.constant.Nop;
import com.whg.ijvm.ch10.instruction.control.Goto;
import com.whg.ijvm.ch10.instruction.control.Return;
import com.whg.ijvm.ch10.instruction.conver.F2x;
import com.whg.ijvm.ch10.instruction.conver.I2x;
import com.whg.ijvm.ch10.instruction.extend.IfNull;
import com.whg.ijvm.ch10.instruction.load.*;
import com.whg.ijvm.ch10.instruction.math.*;
import com.whg.ijvm.ch10.instruction.reference.*;
import com.whg.ijvm.ch10.instruction.reserved.InvokeNative;
import com.whg.ijvm.ch10.instruction.stack.Dup;
import com.whg.ijvm.ch10.instruction.stack.Pop;
import com.whg.ijvm.ch10.instruction.store.*;

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
            case 0x0b:
                return new Const.FCONST_0();
            case 0x10:
                return new Ipush.BIPUSH();
            case 0x11:
                return new Ipush.SIPUSH();

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
            case 0x22:
                return new FLoad.FLOAD_0();
            case 0x23:
                return new FLoad.FLOAD_1();
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
            case 0x34:
                return new ArrLoad.CALOAD();
            case 0x3a:
                return new AStore.ASTORE();
            case 0x3b:
                return new IStore.ISTORE_0();
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

            case 0x53:
                return new ArrStore.AASTORE();
            case 0x55:
                return new ArrStore.CASTORE();
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
            case 0x6c:
                return new Div.IDIV();

            case 0x70:
                return new Rem.IREM();
            case 0x74:
                return new Neg.INEG();
            case 0x78:
                return new Sh.ISHL();
            case 0x79:
                return new Sh.LSHL();
            case 0x7a:
                return new Sh.ISHR();
            case 0x7c:
                return new Sh.IUSHR();
            case 0x7e:
                return new And.IAND();
            case 0x7f:
                return new And.LAND();

            case 0x80:
                return new Or.IOR();
            case 0x82:
                return new Xor.IXOR();
            case 0x84:
                return new Inc.IINC();
            case 0x85:
                return new I2x.I2L();
            case 0x86:
                return new I2x.I2F();
            case 0x8b:
                return new F2x.F2I();

            case 0x92:
                return new I2x.I2C();
            case 0x94:
                return new Lcmp.LCMP();
            case 0x95:
                return new Fcmp.FCMPL();
            case 0x96:
                return new Fcmp.FCMPG();
            case 0x99:
                return new IFcond.IFEQ();
            case 0x9a:
                return new IFcond.IFNE();
            case 0x9b:
                return new IFcond.IFLT();
            case 0x9c:
                return new IFcond.IFGE();
            case 0x9d:
                return new IFcond.IFGT();
            case 0x9e:
                return new IFcond.IFLE();
            case 0x9f:
                return new IFIcmp.IF_ICMPEQ();

            case 0xa0:
                return new IFIcmp.IF_ICMPNE();
            case 0xa1:
                return new IFIcmp.IF_ICMPLT();
            case 0xa2:
                return new IFIcmp.IF_ICMPGE();
            case 0xa3:
                return new IFIcmp.IF_ICMPGT();
            case 0xa4:
                return new IFIcmp.IF_ICMPLE();
            case 0xa5:
                return new IFAcmp.IF_ACMPEQ();

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
            case 0xbf:
                return new Athrow();

            case 0xc0:
                return new Instance.CHECK_CAST();
            case 0xc1:
                return new Instance.INSTANCE_OF();

            case 0xc5:
                return new Arr.MULTI_ANEW_ARRAY();
            case 0xc6:
                return new IfNull.IFNULL();
            case 0xc7:
                return new IfNull.IFNONNULL();

            case 0xfe:
                return new InvokeNative();

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
