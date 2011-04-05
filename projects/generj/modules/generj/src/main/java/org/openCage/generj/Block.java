package org.openCage.generj;
import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.NameExpr.NULL;
import static org.openCage.generj.Typ.TYP;

/**
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.public interface Statement {
 */

public class Block<T> implements Statement {

    private List<Statement> statements = new ArrayList<Statement>();
    private T base;

    public Block( T base ) {
        this.base = base;
    }

    public Field<Block<T>> field( Typ typ, NameExpr name ) {
        Field<Block<T>> fld = new Field<Block<T>>( this, "", typ, name );
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

    public Block<T> return_(Expr expr) {
        statements.add( new Return(expr));
        return this;
    }

    public Block<T> return_() {
        statements.add( new Return());
        return this;
    }


    public IfStatement<Block<T>> if_(Expr cond) {
        IfStatement<Block<T>> ex = new IfStatement<Block<T>>( this, cond );
        statements.add( ex );
        return ex;
    }

    public SwitchStatement<Block<T>> switch_(Expr expr) {
        SwitchStatement<Block<T>> ex = new SwitchStatement<Block<T>>( this, expr );
        statements.add( ex );
        return ex;
    }

    public TryStatement<Block<T>> try_() {
        TryStatement<Block<T>> ty = new TryStatement<Block<T>>(this);
        statements.add(ty);
        return ty;
    }


    public WhileExpr<Block<T>> while_(Expr condition) {
        WhileExpr<Block<T>> wh = new WhileExpr<Block<T>>( this, condition );
        statements.add( wh );
        return wh;
    }

    public IfStatement ifNotNull(Expr expr) {
        IfStatement<Block<T>> ex = new IfStatement<Block<T>>( this, Exp.bi( "!=", expr, NULL) );
        statements.add( ex );
        return ex;
    }

    public IfStatement ifNull(Expr expr) {
        IfStatement<Block<T>> ex = new IfStatement<Block<T>>( this, Exp.bi( "==", expr, NULL ) );
        statements.add( ex );
        return ex;
    }

    public ForStatement<Block<T>> for_(Typ typ, String var, Expr expr) {
        ForStatement<Block<T>> ex = new ForStatement<Block<T>>(this, typ, var, expr );
        statements.add(ex);
        return ex;
    }

    public ClassicForExpr<Block<T>> for_(Typ typ, String var, Expr init, Expr condition, Expr incr) {
        ClassicForExpr<Block<T>> ex = new ClassicForExpr<Block<T>>(this, typ, var, init, condition, incr );
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

    public Block<T> throw_(Typ exception, String message) {
        statements.add( new UnOp( "throw", new Cnstr( exception, Str.s(message )) ));
        return this;
    }

    public Block<T> throw_(Expr expr) {
        statements.add( new UnOp( "throw", expr ));
        return this;
    }

    public Block<T> throwIllegalArgument( Expr expr ) {
        statements.add( new UnOp( "throw", new Cnstr( TYP("IllegalArgumentException"), expr )));
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

    public Block<T> _( Statement st ) {
        statements.add( st );
        return this;
    }



}
