package org.openCage.generj;


public class ForExpr<T> implements Statement {
    private T base;
    private Typ typ;
    private String var;
    private Expr expr;
    private Block<ForExpr<T>> body;

    public ForExpr(T base, Typ typ, String var, Expr expr) {
        this.base = base;
        this.typ = typ;
        this.var = var;
        this.expr = expr;
    }

    public Block<ForExpr<T>> body() {
        if ( body == null ) {
            body = new Block<ForExpr<T>>( this );
        }
        return body;
    }

    public String toString() {
        return toString( "" );
    }


    @Override
    public String toString(String prefix) {
        return prefix + "for ( " + typ.toString() + " " + var + " : " + expr.toString() + " ) " + body.toString( prefix  );
    }
}
