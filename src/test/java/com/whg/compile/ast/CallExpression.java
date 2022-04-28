package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallExpression extends BaseASTNode {

    final String name;
    final List<ASTNode> params;

    public CallExpression(ASTNode parent, String name) {
        super(parent);
        this.type = ASTType.CallExpression;
        this.name = name;
        this.params = new ArrayList<>();
    }

    @Override
    public String value() {
        return name;
    }

    @Override
    public void addNode(ASTNode node){
        params.add(node);
    }

    @Override
    public void traverse(Traverser traverser) {
        traverser.traverseList(params);
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
