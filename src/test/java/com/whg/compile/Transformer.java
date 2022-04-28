package com.whg.compile;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.Program;
import com.whg.compile.transform.Traverser;

public class Transformer {

    /**
     * 通过在旧的AST节点上创建一个属性来引用新的AST上的列表属性，
     * 这样就可以在遍历旧的树时往新的树的列表里添加节点。
     */
    ASTNode transform(ASTNode node){
        Program program = new Program();
        node.setContext(program.children());
        new Traverser().traverse(node);
        System.out.println(program);
        return program;
    }

}
