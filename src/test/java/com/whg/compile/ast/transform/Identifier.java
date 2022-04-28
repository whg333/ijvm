package com.whg.compile.ast.transform;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.BaseASTNode;

public class Identifier extends BaseASTNode {

    final String name;

    public Identifier(ASTNode parent, String name){
        super(parent);
        type = ASTType.Identifier;
        this.name = name;
    }

    @Override
    public String value() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"name\": \"" + name + "\"" +
                '}';
    }
}
