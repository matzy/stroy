package org.openCage.geni;

import java.util.ArrayList;
import java.util.List;

public class Block<T> implements Statement {

    private List<Statement> statements = new ArrayList<Statement>();
    private T base;

    public Block( T base ) {
        this.base = base;
    }

    public Fild<Block> fild( Typ typ, String name ) {
        Fild<Block> fld = new Fild<Block>( this, "", typ, name );
        statements.add( fld );
        return fld;
    }

    public Block assign(String var, Expr str) {
        statements.add( new Assign(var,str));
        return this;
    }

    public Block assignPlus(String var, Expr expr) {
        statements.add( new Assign(var,expr).plus());
        return this;
    }

    public Block retrn( Expr expr ) {
        statements.add( new Return(expr));
        return this;
    }

    public IfExpr<Block> iff( Expr cond ) {
        IfExpr<Block> ex = new IfExpr<Block>( this, cond );
        statements.add( ex );
        return ex;
    }



}
