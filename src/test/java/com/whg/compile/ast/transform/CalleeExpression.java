package com.whg.compile.ast.transform;

import com.whg.compile.ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalleeExpression extends ASTComposite {

    public final Identifier callee;
    public final List<ASTNode> arguments;

    public CalleeExpression(CallExpression callExpression) {
        super(callExpression);
        type = ASTType.CalleeExpression;
        callee = new Identifier(parent, callExpression.value());
        arguments = new ArrayList<>();
    }

    @Override
    public List<ASTNode> children() {
        return arguments;
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
