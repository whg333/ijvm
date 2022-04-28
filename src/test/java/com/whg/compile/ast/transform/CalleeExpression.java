package com.whg.compile.ast.transform;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.BaseASTNode;
import com.whg.compile.ast.CallExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalleeExpression extends BaseASTNode {

    public final Identifier callee;
    public final List<ASTNode> arguments;

    public CalleeExpression(ASTNode parent, CallExpression callExpression) {
        super(parent);
        type = ASTType.CalleeExpression;
        callee = new Identifier(parent, callExpression.value());
        arguments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"callee\": " + callee +
                ", \"arguments\": " + Arrays.toString(arguments.toArray()) +
                '}';
    }
}
