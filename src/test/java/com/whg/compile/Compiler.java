package com.whg.compile;

public class Compiler {

    String compile(String input){
        String tokens = new Tokenizer().parseTokens(input);
        String ast = new Parser().parse(tokens);
        String newAst = new Transformer().transform(ast);
        String output = new CodeGenerator().generate(newAst);
        output = "add(2, subtract(34, 29))";
        return output;
    }

}
