package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

public interface ASTNode {

    ASTType type();

    Object value();

    ASTNode parent();

    void addNode(ASTNode node);

    void traverse(Traverser traverser);

}
