package com.whg.compile;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.transform.CalleeExpression;
import com.whg.compile.ast.transform.ExpressionStatement;

import java.util.stream.Collectors;

public class CodeGenerator {

    String generate(ASTNode node){
        switch (node.type()){
            case Program:
                return node.children().stream().map(this::generate)
                        .collect(Collectors.joining("\n"));
            case ExpressionStatement:
                return generate(((ExpressionStatement)node).expression)/*+';'*/;
            case CalleeExpression:
                return generate(((CalleeExpression)node).callee)
                        + '('
                        + ((CalleeExpression)node).arguments.stream().map(this::generate)
                            .collect(Collectors.joining(", "))
                        + ')';
            case Identifier:
            case NumberLiteral:
                return node.value()+"";
            default:
                throw new IllegalArgumentException("Unknown ast type: "+node.type());
        }
    }

}
