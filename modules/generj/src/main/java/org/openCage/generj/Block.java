package org.openCage.generj;
import java.util.ArrayList;
import java.util.List;

public class Block<T> implements Statement {

    private List<Statement> statements = new ArrayList<Statement>();
    private T base;

    public Block( T base ) {
        this.base = base;
    }

    public Fild<Block<T>> fild( Typ typ, String name ) {
        Fild<Block<T>> fld = new Fild<Block<T>>( this, "", typ, name );
        statements.add( fld );
        return fld;
    }

    public Block<T> assign(String var, Expr str) {
        statements.add( new Assign(var,str));
        return this;
    }

    public Block<T> assignPlus(String var, Expr expr) {
        statements.add( new Assign(var,expr).plus());
        return this;
    }

    public Block<T> retrn( Expr expr ) {
        statements.add( new Return(expr));
        return this;
    }


    public IfExpr<Block<T>> iff( Expr cond ) {
        IfExpr<Block<T>> ex = new IfExpr<Block<T>>( this, cond );
        statements.add( ex );
        return ex;
    }

    public ForExpr<Block<T>> fr( Typ typ, String var, Expr expr ) {
        ForExpr<Block<T>> ex = new ForExpr<Block<T>>(this, typ, var, expr );
        statements.add(ex);
        return ex;
    }

    public String toString(String prefix, boolean std ) {
        String ret = "";

        if ( std ) {
            ret += "{\n";
        } else {
            ret += prefix + "{\n";
        }

        for ( Statement st : statements ) {
            ret += st.toString( prefix + "   ") + ";\n";
        }
        ret += prefix + "}";

        return ret;
    }

    public Block<T> thrw( Typ exception, String message ) {
        statements.add( new UnOp( "throw", new Cnstr( exception, Str.s(message )) ));
        return this;
    }

    public Block<T> call( String name, Expr ... exprs ) {
        statements.add( new Call( name, exprs ));
        return this;
    }

    @Override public String toString(String prefix) {
        return toString( prefix, true );
    }

    public T r() {
        return base;
    }
}