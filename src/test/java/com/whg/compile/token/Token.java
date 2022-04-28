package com.whg.compile.token;

public class Token {

    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean isLeftParen(){
        return isParen() && value.equals("(");
    }

    public boolean isRightParen(){
        return isParen() && value.equals(")");
    }

    public boolean isParen(){
        return type == TokenType.paren;
    }

    public boolean isNumber(){
        return type == TokenType.number;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\": \"" + type + "\"" +
                ", \"value\": \"" + value + "\"" +
                '}';
    }

}
