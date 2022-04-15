package com.whg.ijvm.ch09.heap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClassNameHelp {

    private static final Map<String, String> primitiveTypes = new HashMap<String, String>(){
        {
            put("void", "V");
            put("boolean", "Z");
            put("byte", "B");
            put("short", "S");
            put("int", "I");
            put("long", "J");
            put("char", "C");
            put("float", "F");
            put("double", "D");
        }
    };

    public static Collection<String> primitiveTypeKeys(){
        return primitiveTypes.keySet();
    }

}
