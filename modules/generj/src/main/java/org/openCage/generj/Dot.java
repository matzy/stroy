package org.openCage.generj;

import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Str.STR;

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
public class Dot implements Callble {
    private Expr left;
    private NameExpr name;

    public Dot( Expr left, NameExpr name ) {
        this.left = left;
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

    public static Dot DOT( String str, NameExpr name ) {
        return new Dot( NAME(str), name );
    }

    public static Dot DOT( String str, String str2 ) {
        return new Dot( NAME(str), NAME(str2) );
    }


}
