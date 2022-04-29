package com.whg.compile.ast.token;

import com.whg.compile.ast.ASTComposite;
import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallExpression extends ASTComposite {

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
    public void addChild(ASTNode node){
        params.add(node);
    }

    @Override
    public List<ASTNode> children() {
        return params;
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
