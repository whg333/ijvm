package com.whg.compile.ast;

public class BaseASTNode implements ASTNode{

    protected ASTType type;

    @Override
    public ASTType type() {
        return type;
    }

    @Override
    public void traverse() {

    }

}
