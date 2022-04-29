package com.whg.compile.transform.traverse;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.transform.ASTNodeHandler;
import com.whg.compile.transform.BaseASTNodeHandler;

import java.util.EnumMap;
import java.util.List;

public class BaseTraverser implements ITraverser{

    protected final EnumMap<ASTType, ASTNodeHandler> handlerMap = new EnumMap<>(ASTType.class);

    public BaseTraverser(){
        init();
    }

    protected void init(){
        handlerMap.put(ASTType.Program, new BaseASTNodeHandler());
        handlerMap.put(ASTType.CallExpression, new BaseASTNodeHandler());
        handlerMap.put(ASTType.NumberLiteral, new BaseASTNodeHandler());

        handlerMap.put(ASTType.ExpressionStatement, new BaseASTNodeHandler());
        handlerMap.put(ASTType.CalleeExpression, new BaseASTNodeHandler());
    }

    @Override
    public void traverse(ASTNode ast){
        traverseNode(ast);
    }

    @Override
    public void traverseList(List<ASTNode> list){
        list.forEach(this::traverseNode);
    }

    private void traverseNode(ASTNode node){
        ASTType type = node.type();
        ASTNodeHandler handler = handlerMap.get(type);
        if(handler != null){
            handler.enter(node);
        }

        node.traverse(this);

        if(handler != null){
            handler.exist(node);
        }
    }

}
