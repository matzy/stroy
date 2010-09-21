package org.openCage.generj;

import java.util.ArrayList;
import java.util.List;

public class TryStatement<T> implements Statement {

    public static class Case<T> {
        public Typ typ;
        public NameExpr var;
        public Block<TryStatement<T>> block;

        public Case( Typ typ, NameExpr var, Block<TryStatement<T>> block ) {
            this.typ = typ;
            this.var = var;
            this.block = block;
        }
    }

    private T base;
    private List<Case<T>> cases = new ArrayList<Case<T>>();
    private Block<TryStatement<T>> fnly;
    private Block<TryStatement<T>> trry;

    public TryStatement( T base ) {
        this.base = base;
    }


    public Block<TryStatement<T>> ctch( Typ typ, NameExpr var ) {
        Case<T> cc  = new Case<T>( typ, var, new Block<TryStatement<T>>( this ));
        cases.add( cc );
        return cc.block;
    }

    public Block<TryStatement<T>> fnly() {
        fnly = new Block<TryStatement<T>>( this );
        return fnly;
    }

    public Block<TryStatement<T>> trry() {
        trry = new Block<TryStatement<T>>( this );
        return trry;
    }

    public String toString() {
        return toString( "" );
    }

    @Override public String toString(String prefix) {
        String ret = prefix + "try ";

        ret += trry.toString( prefix );

        for ( Case<T> cse : cases ) {
            ret += " catch( " + cse.typ.toString() + " " + cse.var.toString() + " ) " + cse.block.toString( prefix );
        }

        if ( fnly != null ) {
            ret += " finally " + fnly.toString( prefix );
        }

        return ret;
    }

    public T r() {
        return base;
    }
}
