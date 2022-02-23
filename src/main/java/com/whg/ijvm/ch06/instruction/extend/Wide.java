package com.whg.ijvm.ch06.instruction.extend;

import com.whg.ijvm.ch06.instruction.Instruction;
import com.whg.ijvm.ch06.instruction.base.BytecodeReader;
import com.whg.ijvm.ch06.runtime.RFrame;

public class Wide implements Instruction {

    Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        short opcode = reader.readUint8().value();
        switch (opcode){
            //TODO
            /*
            case 0x15:
                inst := &loads.ILOAD{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x16:
                inst := &loads.LLOAD{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x17:
                inst := &loads.FLOAD{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x18:
                inst := &loads.DLOAD{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x19:
                inst := &loads.ALOAD{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x36:
                inst := &stores.ISTORE{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x37:
                inst := &stores.LSTORE{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x38:
                inst := &stores.FSTORE{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x39:
                inst := &stores.DSTORE{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x3a:
                inst := &stores.ASTORE{}
            inst.Index = uint(reader.ReadUint16())
            self.modifiedInstruction = inst
            case 0x84:
                inst := &math.IINC{}
            inst.Index = uint(reader.ReadUint16())
            inst.Const = int32(reader.ReadInt16())
            self.modifiedInstruction = inst
            */

            case 0xa9:
            default:
                throw new UnsupportedOperationException("opcode="+opcode);
        }
    }

    @Override
    public void execute(RFrame frame) {
        modifiedInstruction.execute(frame);
    }

}
