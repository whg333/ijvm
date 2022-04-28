package com.whg.compile.ast;

import com.whg.compile.transform.Traverser;

import java.util.List;

public interface ASTNode {

    ASTType type();

    Object value();

    ASTNode parent();

    void addChild(ASTNode node);

    List<ASTNode> children();

    void traverse(Traverser traverser);

    /** 设置新语法树的节点列表，用于收集子节点 */
    void setContext(List<ASTNode> context);

    void addContextNode(ASTNode node);

}
