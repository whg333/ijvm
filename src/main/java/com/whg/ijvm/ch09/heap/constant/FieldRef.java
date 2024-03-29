package com.whg.ijvm.ch09.heap.constant;

import com.whg.ijvm.ch09.classfile.constantinfo.member.FieldRefInfo;
import com.whg.ijvm.ch09.heap.RClass;
import com.whg.ijvm.ch09.heap.RConstantPool;
import com.whg.ijvm.ch09.heap.RField;

public class FieldRef extends MemberRef{

    RField field;

    public FieldRef(RConstantPool cp, FieldRefInfo refInfo) {
        super(cp, refInfo);
    }

    public RField resolveField(){
        if(field == null){
            resolveFieldRef();
        }
        return field;
    }

    private void resolveFieldRef() {
        RClass d = cp.getClazz();
        RClass c = resolveClass();
        RField field = lookupField(c, name, descriptor);
        if(field == null){
            throw new RuntimeException("NoSuchFieldError");
        }
        if(!field.isAccessibleTo(d)){
            throw new RuntimeException("IllegalAccessError");
        }
        this.field = field;
    }

    private RField lookupField(RClass c, String name, String descriptor) {
        for(RField field: c.getFields()){
            if(field.isMatch(name, descriptor)){
                return field;
            }
        }
        for(RClass iface: c.getInterfaces()){
            RField field = lookupField(iface, name, descriptor);
            if(field != null){
                return field;
            }
        }
        RClass superClass = c.getSuperClass();
        if(superClass != null){
            return lookupField(superClass, name, descriptor);
        }
        return null;
    }

}
