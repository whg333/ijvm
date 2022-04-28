package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

public interface ASTNode {

    ASTType type();

    ASTNode parent();

    void traverse(Traverser traverser);

}
