package com.whg.compile;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.Program;
import com.whg.compile.transform.Traverser;

public class Transformer {

    ASTNode transform(ASTNode ast){
        Program program = new Program();
        new Traverser(program).traverse(ast);
        System.out.println(program);
        return program;
    }

}
