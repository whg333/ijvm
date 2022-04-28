package com.whg.compile.transform;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.CallExpression;
import com.whg.compile.ast.NumberLiteral;
import com.whg.compile.ast.transform.CalleeExpression;
import com.whg.compile.ast.transform.ExpressionStatement;

import java.util.EnumMap;
import java.util.List;

public class Traverser {

    private static final EnumMap<ASTType, ASTNodeHandler> handlerMap = new EnumMap<>(ASTType.class);
    static {
        handlerMap.put(ASTType.Program, new BaseASTNodeHandler());
        handlerMap.put(ASTType.CallExpression, new BaseASTNodeHandler(){
            @Override
            public void enter(ASTNode node) {
                super.enter(node);
                ASTNode parent = node.parent();
                ASTNode expression = new CalleeExpression(parent, (CallExpression) node);
                node.setContext(((CalleeExpression) expression).arguments);
                if(parent.type() != ASTType.CallExpression){
                    expression = new ExpressionStatement(parent, (CalleeExpression) expression);
                }
                parent.addContextNode(expression);
            }
        });
        handlerMap.put(ASTType.NumberLiteral, new BaseASTNodeHandler(){
            @Override
            public void enter(ASTNode node) {
                super.enter(node);
                ASTNode parent = node.parent();
                parent.addContextNode(new NumberLiteral(parent, (Number) node.value()));
            }
        });
    }

    public void traverse(ASTNode ast){
        traverseNode(ast);
    }

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
