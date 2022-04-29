package com.whg.compile.transform.traverse;

import com.whg.compile.ast.ASTNode;

import java.util.List;

public interface ITraverser {

    void traverse(ASTNode ast);

    void traverseList(List<ASTNode> list);

}
