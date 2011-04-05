package org.openCage.generj;

import java.util.ArrayList;
import java.util.List;

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


    public Block<TryStatement<T>> _catch( Typ typ, NameExpr var ) {
        Case<T> cc  = new Case<T>( typ, var, new Block<TryStatement<T>>( this ));
        cases.add( cc );
        return cc.block;
    }

    public Block<TryStatement<T>> _finally() {
        fnly = new Block<TryStatement<T>>( this );
        return fnly;
    }

    public Block<TryStatement<T>> _try() {
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
