package com.whg.compile.ast;

public interface ASTNode {

    ASTType type();

    ASTNode parent();

    void traverse();

}
