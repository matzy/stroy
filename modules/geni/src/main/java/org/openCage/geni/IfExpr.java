package org.openCage.geni;

import java.util.ArrayList;
import java.util.List;

public class IfExpr<T> implements Statement {
    private Expr cond;
    private T base;
    private Block<IfExpr<T>> thn;
    private Block<IfExpr<T>> els;

    public IfExpr( T base, Expr cond ) {
        this.cond = cond;
        this.base = base;
    }


    public Block<IfExpr<T>> thn() {
        if ( thn == null ) {
            thn = new Block<IfExpr<T>>( this );
        }
        return thn;
    }

    public Block<IfExpr<T>> els() {
        if ( els == null ) {
            els = new Block<IfExpr<T>>( this );
        }
        return els;
    }

    public String toString() {
        String ret = "if( " + cond.toString() + " )";

        ret += thn.toString( "   ", true );

        if ( els != null ) {
            ret += " else ";
            ret += els.toString( "   ", true );
        }

        return ret;
    }

    public T r() {
        return base;
    }
}
