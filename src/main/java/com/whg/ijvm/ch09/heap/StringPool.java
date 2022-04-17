package com.whg.ijvm.ch09.heap;

import java.util.HashMap;
import java.util.Map;

public class StringPool {

    static Map<String, RObject> internedStrings = new HashMap<>();

    public static RObject JString(RClassLoader loader, String str){
        RObject internedStr = internedStrings.get(str);
        if(internedStr != null){
            return internedStr;
        }

        char[] chars = str.toCharArray();
        RArray jChars = new RArray(loader.loadClass("[C"), chars);

        RObject jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVar("value", "[C", jChars);

        internedStrings.put(str, jStr);
        return jStr;
    }

    public static RObject internString(RObject jStr){
        String goStr = goString(jStr);
        RObject internedStr = internedStrings.get(goStr);
        if(internedStr != null){
            return internedStr;
        }
        internedStrings.put(goStr, jStr);
        return jStr;
    }

    public static String goString(RObject jStr) {
        RArray charArr = jStr.getRefVar("value", "[C");
        return new String(charArr.getChars());
    }

}
