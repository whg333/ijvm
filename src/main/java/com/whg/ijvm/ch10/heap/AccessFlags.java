package com.whg.ijvm.ch10.heap;

public interface AccessFlags {

    int ACC_PUBLIC          = 0x0001; // class field method
    int ACC_PRIVATE         = 0x0002; //       field method
    int ACC_PROTECTED       = 0x0004; //       field method
    int ACC_STATIC          = 0x0008; //       field method
    int ACC_FINAL           = 0x0010; // class field method
    int ACC_SUPER           = 0x0020; // class
    int ACC_SYNCHRONIZED    = 0x0020; //             method
    int ACC_VOLATILE        = 0x0040; //       field
    int ACC_BRIDGE          = 0x0040; //             method
    int ACC_TRANSIENT       = 0x0080; //       field
    int ACC_VARARGS         = 0x0080; //             method
    int ACC_NATIVE          = 0x0100; //             method
    int ACC_INTERFACE       = 0x0200; // class
    int ACC_ABSTRACT        = 0x0400; // class       method
    int ACC_STRICT          = 0x0800; //             method
    int ACC_SYNTHETIC       = 0x1000; // class field method
    int ACC_ANNOTATION      = 0x2000; // class
    int ACC_ENUM            = 0x4000; // class field

    static boolean isPublic(int accessFlags){
        return (accessFlags & ACC_PUBLIC) != 0;
    }
    static boolean isPrivate(int accessFlags){
        return (accessFlags & ACC_PRIVATE) != 0;
    }
    static boolean isProtected(int accessFlags){
        return (accessFlags & ACC_PROTECTED) != 0;
    }
    static boolean isStatic(int accessFlags){
        return (accessFlags & ACC_STATIC) != 0;
    }
    static boolean isFinal(int accessFlags){
        return (accessFlags & ACC_FINAL) != 0;
    }
    static boolean isSuper(int accessFlags){
        return (accessFlags & ACC_SUPER) != 0;
    }
    static boolean isNative(int accessFlags) {
        return (accessFlags & ACC_NATIVE) != 0;
    }
    static boolean isInterface(int accessFlags){
        return (accessFlags & ACC_INTERFACE) != 0;
    }
    static boolean isAbstract(int accessFlags){
        return (accessFlags & ACC_ABSTRACT) != 0;
    }
    static boolean isSynthetic(int accessFlags){
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }
    static boolean isAnnotation(int accessFlags){
        return (accessFlags & ACC_ANNOTATION) != 0;
    }
    static boolean isEnum(int accessFlags){
        return (accessFlags & ACC_ENUM) != 0;
    }

}
