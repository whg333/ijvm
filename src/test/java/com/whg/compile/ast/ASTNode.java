package com.whg.compile.ast;

import com.whg.compile.transform.traverse.ITraverser;

import java.util.List;

public interface ASTNode {

    ASTType type();

    Object value();

    ASTNode parent();

    void addChild(ASTNode node);

    List<ASTNode> children();

    void traverse(ITraverser traverser);

    /** 设置新语法树的节点列表，配合addContextNode收集新语法树的子节点 */
    void setContext(List<ASTNode> context);

    void addContextNode(ASTNode node);

}
