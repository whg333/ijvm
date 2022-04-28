package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

import java.util.List;

public class BaseASTNode implements ASTNode{

    protected ASTType type;
    protected final ASTNode parent;

    protected List<ASTNode> context;

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

    @Override
    public void setContext(List<ASTNode> context) {
        this.context = context;
    }

    @Override
    public void addContextNode(ASTNode node) {
        context.add(node);
    }

}
