package com.whg.compile.ast.token;

import com.whg.compile.ast.ASTComposite;
import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program extends ASTComposite {

    final List<ASTNode> body;

    public Program() {
        super(null);
        this.type = ASTType.Program;
        this.body = new ArrayList<>();
    }

    @Override
    public void addChild(ASTNode node){
        body.add(node);
    }

    @Override
    public List<ASTNode> children() {
        return body;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"body\" :" + Arrays.toString(body.toArray()) +
                '}';
    }

}
