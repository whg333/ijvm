package com.whg.compile.ast;

public class NumberLiteral implements ASTNode {

    final ASTType type;
    final Number value;

    public NumberLiteral(Number value) {
        this.type = ASTType.NumberLiteral;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"value\": " + value +
                '}';
    }
}
