package org.openCage.generj;

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
