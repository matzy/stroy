package org.openCage.generj;

public class Assign implements Statement {
    private Expr expr;
    private Callble var;
    private boolean plus;

    public Assign( Callble var, Expr expr) {
        this.var = var;
        this.expr = expr;
    }

    public String toString() {
        return var + (plus ? " +" : " " ) + "= " + expr.toString() + ";";
    }

    public Assign plus() {
        this.plus = true;
        return this;
    }

    @Override public String toString(String prefix) {
        return prefix + toString();
    }
}
