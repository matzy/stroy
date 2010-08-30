package org.openCage.generj;

public class Cast implements Expr { 
    private Typ typ;
    private Expr expr;

    public Cast( Typ typ, Expr expr) {
        this.typ = typ;
        this.expr = expr;
    }

    public static Cast CAST( Typ typ, Expr expr) {
        return new Cast( typ,expr);
    }

    public String toString() {
        return "((" + typ + ")" + expr + ")";
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
