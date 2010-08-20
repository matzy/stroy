package org.openCage.geni;

public class Assign implements Statement {
    private Expr expr;
    private String var;
    private boolean plus;

    public Assign( String var, Expr expr) {
        this.var = var;
        this.expr = expr;
    }

    public String toString() {
        return var + (plus ? " +" : " " ) + "= " + expr.toString();
    }

    public Assign plus() {
        this.plus = true;
        return this;
    }
}
