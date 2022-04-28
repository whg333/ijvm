package com.whg.compile.ast.transform;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.BaseASTNode;

public class ExpressionStatement extends BaseASTNode {

    CalleeExpression expression;

    public ExpressionStatement(ASTNode parent, CalleeExpression expression) {
        super(parent);
        type = ASTType.ExpressionStatement;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"expression\": " + expression +
                '}';
    }
}
