package com.whg.compile.ast.transform;

import com.whg.compile.ast.ASTComposite;
import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;

import java.util.Collections;
import java.util.List;

public class ExpressionStatement extends ASTComposite {

    public final CalleeExpression expression;

    public ExpressionStatement(ASTNode parent, CalleeExpression expression) {
        super(parent);
        type = ASTType.ExpressionStatement;
        this.expression = expression;
    }

    @Override
    public List<ASTNode> children() {
        return Collections.singletonList(expression);
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"expression\": " + expression +
                '}';
    }
}
