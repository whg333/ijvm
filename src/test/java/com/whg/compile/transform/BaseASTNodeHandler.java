package com.whg.compile.transform;

import com.whg.compile.ast.ASTNode;

public class BaseASTNodeHandler implements ASTNodeHandler{

    @Override
    public void enter(ASTNode node) {
        ASTNode parent = node.parent();
        String retract = addRetract(calcLevel(node));
        System.out.println(retract+"enter: type="+node.type()+"("+node.hashCode()+")"
                +", parent="+(parent != null ? parent.type()+"("+parent.hashCode()+")" : ""));
    }

    @Override
    public void exist(ASTNode node) {
        ASTNode parent = node.parent();
        String retract = addRetract(calcLevel(node));
        System.out.println(retract+"exist: type="+node.type()+"("+node.hashCode()+")"
                +", parent="+(parent != null ? parent.type()+"("+parent.hashCode()+")" : ""));
    }

    private String addRetract(int level){
        StringBuilder sb = new StringBuilder();
        while(level > 0){
            sb.append('\t');
            level--;
        }
        return sb.toString();
    }

    private int calcLevel(ASTNode node){
        int level = 0;
        ASTNode parent = node.parent();
        while(parent != null){
            level++;
            parent = parent.parent();
        }
        return level;
    }

}
