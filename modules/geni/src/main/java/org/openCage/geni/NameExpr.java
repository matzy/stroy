package org.openCage.geni;

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
}
