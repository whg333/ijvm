package com.whg.compile.transform;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;

import java.util.EnumMap;
import java.util.List;

public class Traverser {

    private static final EnumMap<ASTType, ASTNodeHandler> handlerMap = new EnumMap<>(ASTType.class);
    static {
        handlerMap.put(ASTType.Program, new BaseASTNodeHandler());
        handlerMap.put(ASTType.CallExpression, new BaseASTNodeHandler());
        handlerMap.put(ASTType.NumberLiteral, new BaseASTNodeHandler());
    }

    public static void traverse(ASTNode ast){
        traverseNode(ast);
    }

    public static void traverseList(List<ASTNode> list){
        list.forEach(node -> traverseNode(node));
    }

    static void traverseNode(ASTNode node){
        ASTType type = node.type();
        ASTNodeHandler handler = handlerMap.get(type);
        if(handler != null){
            handler.enter(node);
        }

        node.traverse();

        if(handler != null){
            handler.exist(node);
        }
    }

}
