package com.whg.compile.ast.transform;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.BaseASTNode;
import com.whg.compile.transform.ITraverser;
import com.whg.compile.transform.Traverser;

public class ExpressionStatement extends BaseASTNode {

    public final CalleeExpression expression;

    public ExpressionStatement(ASTNode parent, CalleeExpression expression) {
        super(parent);
        type = ASTType.ExpressionStatement;
        this.expression = expression;
    }

    @Override
    public void traverse(ITraverser traverser) {
        traverser.traverse(expression);
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"expression\": " + expression +
                '}';
    }
}
