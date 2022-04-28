package com.whg.compile.ast.transform;

public class Identifier {

    final String type = "Identifier";
    final String name;

    public Identifier(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"name\": \"" + name + "\"" +
                '}';
    }
}
