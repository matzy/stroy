package org.openCage.generj;

import static org.openCage.generj.BracketExpr.BRACKET;
import static org.openCage.generj.NameExpr.NAME;


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

public class BinOp implements Expr {
    private Expr right;
    private Expr left;
    private String op;

    public BinOp( String op, Expr left, Expr right ) {
        this.op = op;
        this.left = left;
        this.right = right;
    }


    public String toString() {
        return left.toString() + " " + op + " " + right.toString();
    }

    @Override public String toString(String prefix) {
        return prefix + toString();
    }

    public static BinOp INSTANCEOF( Expr expr, Typ typ ) {
        return new BinOp( "instanceof", expr, NAME(typ.getName()) ); // TODO hack 
    }

//    public static BinOp PLUS( Expr left, Expr right ) {
//        return new BinOp( "+", left, right );
//    }

    public static BinOp PLUS( Expr one, Expr two, Expr ... more  ) {
        BinOp ret = new BinOp( "+", one, two );
        for ( Expr next : more ) {
            ret = new BinOp( "+", ret, next );
        }
        return ret;
    }

    public static BinOp LESS( Expr left, Expr right ) {
        return new BinOp( "<", left, right );
    }

    public static BinOp AND( Expr left, Expr right ) {
        return new BinOp( "&&", BRACKET(left), BRACKET(right ));
    }

    public static BinOp OR( Expr left, Expr right ) {
        return new BinOp( "||", BRACKET(left), BRACKET(right ));
    }

}
