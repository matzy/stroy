package org.openCage.generj;


public class NameExpr implements Expr {
    private String str;

    public NameExpr( String str ) {
        this.str = str;
    }

    public String toString() {
        return str;
    }

    public static NameExpr n( String str ) {
        return new NameExpr( str );
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
