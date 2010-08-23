package org.openCage.geni;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 20, 2010
 * Time: 3:12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Return implements Statement{
    private Expr expr;

    public Return( Expr expr ) {
        this.expr = expr;
    }

    public String toString() {
        return "return " + expr.toString();
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
