package com.whg.ijvm.ch06.heap;

import com.whg.ijvm.ch06.classfile.MemberInfo;

public abstract class RClassMember {

    int accessFlags;
    String name;
    String descriptor;
    RClass clazz;

    protected void copyMemberInfo(MemberInfo memberInfo){
        accessFlags = memberInfo.getAccessFlags().value();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
    }

    abstract void copyAttributes(MemberInfo cfFiled);

    public boolean isPublic(){
        return AccessFlags.isPublic(accessFlags);
    }
    public boolean isFinal() {
        return AccessFlags.isFinal(accessFlags);
    }

    public boolean isStatic(){
        return AccessFlags.isStatic(accessFlags);
    }

    public boolean isProtected(){
        return AccessFlags.isProtected(accessFlags);
    }
    public boolean isPrivate(){
        return AccessFlags.isPrivate(accessFlags);
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

    public boolean isAccessibleTo(RClass d){
        if(isPublic()){
            return true;
        }
        RClass c = clazz;
        if(isProtected()){
            return d == c || d.isSubClassOf(c)
                    || d.isSamePackage(c);
        }
        if(!isPrivate()){
            return d.isSamePackage(c);
        }
        return d == c;
    }

    public boolean isMatch(String name, String descriptor){
        return this.name.equals(name)
                && this.descriptor.equals(descriptor);
    }

    public String getName() {
        return name;
    }

    /** setter/getter */
    public String getDescriptor() {
        return descriptor;
    }

    public RClass getRClass() {
        return clazz;
    }

}
