package org.openCage.generj;

import static org.openCage.generj.BracketExpr.BRACKET;
import static org.openCage.generj.NameExpr.NAME;


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
