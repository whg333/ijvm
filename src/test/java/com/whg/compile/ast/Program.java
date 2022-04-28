package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program extends BaseASTNode {

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
    public void traverse(Traverser traverser) {
        traverser.traverseList(body);
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"body\" :" + Arrays.toString(body.toArray()) +
                '}';
    }

}
