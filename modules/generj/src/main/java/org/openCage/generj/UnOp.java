package org.openCage.generj;


public class UnOp implements Expr {
    private Expr expr;
    private String op;

    public UnOp( String op, Expr expr ) {
        this.op = op;
        this.expr = expr;
    }


    public String toString() {
        return op + " " + expr;
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static UnOp NOT( Expr expr ) {
        return new UnOp( "!", expr );
    }
}
