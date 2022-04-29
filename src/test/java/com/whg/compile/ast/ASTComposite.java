package com.whg.compile.ast;

import com.whg.compile.transform.traverse.ITraverser;

/**
 * 组合节点，即标记带有孩子的节点
 */
public class ASTComposite extends BaseASTNode{

    public ASTComposite(ASTNode parent) {
        super(parent);
    }

    @Override
    public void traverse(ITraverser traverser) {
        traverser.traverseList(children());
    }

}
