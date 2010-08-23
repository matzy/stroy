package org.openCage.generj;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 21, 2010
 * Time: 10:08:34 PM
 * To change this template use File | Settings | File Templates.
 */
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
}
