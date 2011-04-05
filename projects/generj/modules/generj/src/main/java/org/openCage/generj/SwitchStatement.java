package org.openCage.generj;

import org.openCage.lang.iterators.Iterators;
import org.openCage.lang.structure.T2;

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
public class SwitchStatement<T> implements Statement {
    private Expr expr;
    private T base;
    private List<Expr> cases = new ArrayList<Expr>();
    private List<Block<SwitchStatement<T>>> caseBlocks = new ArrayList<Block<SwitchStatement<T>>>();
    private Block<SwitchStatement<T>> dflt;

    public SwitchStatement( T base, Expr expr ) {
        this.expr = expr;
        this.base = base;
    }


    public Block<SwitchStatement<T>> case_(Expr cse) {
        cases.add( cse );
        return add( caseBlocks, new Block<SwitchStatement<T>>( this ));
    }

    public Block<SwitchStatement<T>> _default() {
        dflt = new Block<SwitchStatement<T>>( this );
        return dflt;
    }


    public String toString() {
        return toString( "" );
    }

    @Override public String toString(String prefix) {
        String ret = prefix + "switch( " + expr.toString() + " ) {\n" ;

        boolean first = true;
        for ( T2<Expr,Block<SwitchStatement<T>>> cc : Iterators.parallel( cases, caseBlocks )) {
            if ( first ) {
                ret += prefix + "   case " + cc.i0.toString() + ": ";
                first = false;
            } else {
                ret += " case " + cc.i0.toString() + ": ";                
            }
            ret += cc.i1.toString( prefix + "   ");

        }

        if ( dflt != null ) {
            ret += " default: " + dflt.toString( prefix + "   ");

        }

        return ret + "\n" + prefix + "}";

    }

    public T r() {
        return base;
    }

    public static <E> E add( List<E> list, E e ) {
        list.add( e );
        return e;
    }


}

