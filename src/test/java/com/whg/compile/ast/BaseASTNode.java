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
    public ASTNode parent() {
        return parent;
    }

    @Override
    public void traverse(Traverser traverser) {

    }

}
