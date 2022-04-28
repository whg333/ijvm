package com.whg.compile;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.CallExpression;
import com.whg.compile.ast.NumberLiteral;
import com.whg.compile.ast.Program;
import com.whg.compile.token.Token;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int current;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
        current = 0;
    }

    ASTNode parseAST(){
        Program program = new Program();
        int size = tokens.size();
        while(current < size){
            program.addChild(walk(program));
        }
        System.out.println(program);
        return program;
    }

    private ASTNode walk(ASTNode parent) {
        Token token = tokens.get(current);

        if(token.isNumber()){
            current++;
            return new NumberLiteral(parent, Integer.valueOf(token.value));
        }

        if(token.isLeftParen()){
            token = tokens.get(++current);
            CallExpression callExpression = new CallExpression(parent, token.value);

            token = tokens.get(++current);
            while(!token.isParen() || !token.isRightParen()){
                callExpression.addChild(walk(callExpression));
                token = tokens.get(current);
            }

            current++; // skip the right paren, 跳过右括号
            return callExpression;
        }

        throw new IllegalArgumentException("Unknown token type: "+token.type);
    }

}
