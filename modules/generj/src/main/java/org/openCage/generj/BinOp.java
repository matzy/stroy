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

    public static BinOp INSTANCEOF( Expr expr, Typ typ ) {
        return new BinOp( "instanceof", expr, Exp.n(typ.getName()) ); // TODO hack 
    }

    public static BinOp PLUS( Expr left, Expr right ) {
        return new BinOp( "+", left, right );
    }


}
