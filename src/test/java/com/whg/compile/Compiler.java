package com.whg.compile;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.Parser;
import com.whg.compile.token.Token;
import com.whg.compile.token.Tokenizer;
import com.whg.compile.transform.traverse.BaseTraverser;
import com.whg.compile.transform.Transformer;

import java.util.List;

public class Compiler {

    String compile(String input){
        List<Token> tokens = new Tokenizer().parseTokens(input);
        ASTNode ast = new Parser(tokens).parseAST();
        ASTNode newAst = new Transformer().transform(ast);

        new BaseTraverser().traverse(newAst); // traverse newAst

        String output = new CodeGenerator().generate(newAst);

        return output;
    }

}
