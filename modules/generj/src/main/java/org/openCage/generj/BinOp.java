package org.openCage.generj;


public class BinOp implements Expr {
    private Expr right;
    private Expr left;
    private String op;

    public BinOp( String op, Expr left, Expr right ) {
        this.op = op;
        this.left = left;
        this.right = right;
    }


    public String toString() {
        return left.toString() + " " + op + " " + right.toString();
    }

    @Override public String toString(String prefix) {
        return prefix + toString();
    }
}
