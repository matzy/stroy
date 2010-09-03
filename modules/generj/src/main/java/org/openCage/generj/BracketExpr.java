package org.openCage.generj;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 3, 2010
 * Time: 11:29:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class BracketExpr implements Expr {
    private Expr expr;

    public BracketExpr( Expr expr ) {
        this.expr = expr;
    }

    public String toString() {
        return "(" + expr + ")";
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static BracketExpr BRACKET( Expr expr ) {
        return new BracketExpr( expr );
    }
}
