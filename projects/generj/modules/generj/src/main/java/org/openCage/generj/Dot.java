package org.openCage.generj;

import org.openCage.lang.functions.CatchAll;

import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Str.STR;

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

public class Dot implements Callble {
    private Expr left;
    private NameExpr name;

    public Dot( Expr left, NameExpr name ) {
        this.left = left;
        this.name = name;
    }

    public Dot( Typ typ, NameExpr name ) {
        this.left = NAME( typ.toString() ); // TODO
        this.name = name;
    }

    public String toString() {
        return left.toString() + "." + name.toString();
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static Dot DOT( Expr expr, NameExpr name ) {
        return new Dot( expr, name );
    }

    public static Dot DOT( Expr expr, NameExpr ... names ) {

        Expr ret = expr;

        for ( NameExpr name : names ) {
            ret = new Dot( ret, name );
        }

        return (Dot)ret;
    }

    public static Dot DOT( Typ str, NameExpr name ) {
        return new Dot( str, name );
    }

    public static Dot DOT( String str, NameExpr name ) {
        return new Dot( NAME(str), name );
    }

    public static Dot DOT( String str, String str2 ) {
        return new Dot( NAME(str), NAME(str2) );
    }

    public static Dot DOT( String str, String str2, String str3 ) {
        return new Dot( DOT(NAME(str), NAME(str2)), NAME(str3) );
    }

    public Dot dot( NameExpr name ) {
        return DOT( this, name );
    }

    public Dot dot( String name ) {
        return dot( NAME(name));
    }


    public Statement call( Expr ... args ) {
        return CALL( this, args );
    }
}
