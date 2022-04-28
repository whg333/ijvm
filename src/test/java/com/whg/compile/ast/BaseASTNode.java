package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

public class BaseASTNode implements ASTNode{

    protected ASTType type;
    protected final ASTNode parent;

    public BaseASTNode(ASTNode parent){
        this.parent = parent;
    }

    @Override
    public ASTType type() {
        return type;
    }

    @Override
    public Object value() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ASTNode parent() {
        return parent;
    }

    @Override
    public void addNode(ASTNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void traverse(Traverser traverser) {

    }

}
