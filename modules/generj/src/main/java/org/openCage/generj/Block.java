package org.openCage.generj;
import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.NameExpr.NULL;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
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

    public Block<T> assign(Callble var, Expr str) {
        statements.add( new Assign(var,str));
        return this;
    }

    public Block<T> assignPlus(Callble var, Expr expr) {
        statements.add( new Assign(var,expr).plus());
        return this;
    }

    public Block<T> retrn( Expr expr ) {
        statements.add( new Return(expr));
        return this;
    }

    public Block<T> retrn( ) {
        statements.add( new Return());
        return this;
    }


    public IfExpr<Block<T>> iff( Expr cond ) {
        IfExpr<Block<T>> ex = new IfExpr<Block<T>>( this, cond );
        statements.add( ex );
        return ex;
    }

    public IfExpr ifNotNull(Expr expr) {
        IfExpr<Block<T>> ex = new IfExpr<Block<T>>( this, Exp.bi( "!=", expr, NULL) );
        statements.add( ex );
        return ex;
    }

    public IfExpr ifNull(Expr expr) {
        IfExpr<Block<T>> ex = new IfExpr<Block<T>>( this, Exp.bi( "==", expr, NULL ) );
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
            ret += st.toString( prefix + "   ") + (st instanceof Expr ? ";" : "" ) + "\n";
        }
        ret += prefix + "}";

        return ret;
    }

    public Block<T> thrw( Typ exception, String message ) {
        statements.add( new UnOp( "throw", new Cnstr( exception, Str.s(message )) ));
        return this;
    }

    public Block<T> thrwIllegalArgument( Expr expr ) {
        statements.add( new UnOp( "throw", new Cnstr( Typ.s("IllegalArgumentException"), expr )));
        return this;
    }

    public Block<T> call( Callble name, Expr ... exprs ) {
        statements.add( new Call( name, exprs ));
        return this;
    }

    @Override public String toString(String prefix) {
        return toString( prefix, true );
    }

    public T r() {
        return base;
    }

    public Block<T> comment( String comment ) {
        statements.add( new LineComment( comment ));
        return this;
    }


}
