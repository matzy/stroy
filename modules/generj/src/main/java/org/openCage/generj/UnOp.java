package org.openCage.generj;

import static org.openCage.generj.BracketExpr.BRACKET;


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
public class UnOp implements Expr {
    private Expr expr;
    private String op;

    public UnOp( String op, Expr expr ) {
        this.op = op;
        this.expr = expr;
    }


    public String toString() {
        return op + " " + expr;
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static UnOp NOT( Expr expr ) {
        return new UnOp( "!", expr );
    }

    public static UnOp PLUSPLUS( Expr expr ) {
        if ( expr instanceof NameExpr ) {
            return new UnOp( "++", expr);
        }

        return new UnOp( "++", BRACKET( expr ));
    }


}
