package org.openCage.generj;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 30, 2010
 * Time: 11:58:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dot implements Callble {
    private Expr left;
    private NameExpr name;

    public Dot( Expr left, NameExpr name ) {
        this.left = left;
        this.name = name;
    }

    public String toString() {
        return left.toString() + "." + name.toString();
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static Dot DOT( Expr expr, NameExpr name ) {
        return new Dot( expr, name );
    }

    public static Dot DOT( Expr expr, NameExpr ... names ) {

        Expr ret = expr;

        for ( NameExpr name : names ) {
            ret = new Dot( ret, name );
        }

        return (Dot)ret;
    }

}
