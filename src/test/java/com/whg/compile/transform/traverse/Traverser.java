package com.whg.compile.transform.traverse;

import com.whg.compile.ast.ASTNode;
import com.whg.compile.ast.ASTType;
import com.whg.compile.ast.token.CallExpression;
import com.whg.compile.ast.token.NumberLiteral;
import com.whg.compile.ast.transform.CalleeExpression;
import com.whg.compile.ast.transform.ExpressionStatement;
import com.whg.compile.transform.BaseASTNodeHandler;

public class Traverser extends BaseTraverser{

    @Override
    protected void init() {
        handlerMap.put(ASTType.Program, new BaseASTNodeHandler());
        handlerMap.put(ASTType.CallExpression, new BaseASTNodeHandler(){
            @Override
            public void enter(ASTNode node) {
                super.enter(node);
                ASTNode parent = node.parent();
                ASTNode expression = new CalleeExpression((CallExpression) node);
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
                parent.addContextNode(new NumberLiteral(node, (Number) node.value()));
            }
        });

        handlerMap.put(ASTType.ExpressionStatement, new BaseASTNodeHandler());
        handlerMap.put(ASTType.CalleeExpression, new BaseASTNodeHandler());
    }

}
