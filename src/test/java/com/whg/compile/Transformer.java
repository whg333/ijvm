package com.whg.compile;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.Program;
import com.whg.compile.transform.Traverser;

public class Transformer {

    ASTNode transform(ASTNode ast){
        Program newAst = new Program();
        new Traverser().traverse(ast);
        return newAst;
    }

}
