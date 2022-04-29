package com.whg.compile.ast.token;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.BaseASTNode;

public class NumberLiteral extends BaseASTNode {

    final Number value;

    public NumberLiteral(ASTNode parent, Number value) {
        super(parent);
        this.type = ASTType.NumberLiteral;
        this.value = value;
    }

    @Override
    public Number value() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"value\": " + value +
                '}';
    }

}
