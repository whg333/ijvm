package com.whg.compile.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallExpression implements ASTNode {

    final ASTType type;
    final String name;
    final List<ASTNode> params;

    public CallExpression(String name) {
        this.type = ASTType.CallExpression;
        this.name = name;
        this.params = new ArrayList<>();
    }

    public void addParams(ASTNode element){
        params.add(element);
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"name\": \"" + name + "\"" +
                ", \"params\": " + Arrays.toString(params.toArray()) +
                '}';
    }
}
