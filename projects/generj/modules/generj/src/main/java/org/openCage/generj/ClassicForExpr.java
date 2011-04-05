package org.openCage.generj;

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

public class ClassicForExpr<T> implements Statement {
    private T parent;
    private Typ typ;
    private String var;
    private Expr condition;
    private Expr incr;
    private Block<ClassicForExpr<T>> body;
    private Expr init;

    public ClassicForExpr(T parent, Typ typ, String var, Expr init, Expr condition, Expr incr) {
        this.parent = parent;
        this.typ = typ;
        this.var = var;
        this.condition = condition;
        this.incr = incr;
        this.init = init;
    }

    public Block<ClassicForExpr<T>> body() {
        if ( body == null ) {
            body = new Block<ClassicForExpr<T>>( this );
        }
        return body;
    }

    public String toString() {
        return toString( "" );
    }


    @Override
    public String toString(String prefix) {
        return prefix + "for ( " + typ.toString() + " " + var + " = " + init +"; " + condition.toString() + "; " + incr.toString() + ")"
                           + body.toString( prefix  );
    }
}
