package com.whg.compile.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program implements ASTNode {

    final ASTType type;
    final List<ASTNode> body;

    public Program() {
        this.type = ASTType.Program;
        this.body = new ArrayList<>();
    }

    public void addBody(ASTNode element){
        body.add(element);
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"body\" :" + Arrays.toString(body.toArray()) +
                '}';
    }
}
