package org.openCage.geni;

import org.openCage.geni.Expr;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 20, 2010
 * Time: 2:24:50 PM
 * To change this template use File | Settings | File Templates.
 */
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
}
