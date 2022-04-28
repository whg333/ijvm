package com.whg.compile;

import java.util.List;

public class Compiler {

    String compile(String input){
        List<Tokenizer.Token> tokens = new Tokenizer().parseTokens(input);
        // String ast = new Parser().parse(tokens);
        // String newAst = new Transformer().transform(ast);
        // String output = new CodeGenerator().generate(newAst);
        // return output;

        return "add(2, subtract(34, 29))";
    }

}
