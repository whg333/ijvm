package com.whg.compile.transform;

import com.whg.compile.ast.ASTNode;

public interface ASTNodeHandler {

    void enter(ASTNode node, ASTNode newAst);

    void exist(ASTNode node, ASTNode newAst);

}
