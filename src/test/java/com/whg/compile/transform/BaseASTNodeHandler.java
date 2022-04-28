package com.whg.compile.transform;

import com.whg.compile.ast.ASTNode;

public class BaseASTNodeHandler implements ASTNodeHandler{

    @Override
    public void enter(ASTNode node, ASTNode parent) {
        System.out.println("enter: type="+node.type()+", parent="+(parent != null ? parent.type() : ""));
    }

    @Override
    public void exist(ASTNode node, ASTNode parent) {
        System.out.println("exist: type="+node.type()+", parent="+(parent != null ? parent.type() : ""));
    }

}
