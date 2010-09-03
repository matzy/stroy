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
public class Fild<T> implements Statement {
    private NameExpr name;
    private T clazz;
    private String mod;
    private Typ typ;
    private Expr init;

    public Fild( T clazz, String mod, Typ typ, NameExpr name) {
        this.name = name;
        this.clazz = clazz;
        this.mod = mod;
        this.typ = typ;
    }

    public T c() {
        return clazz;
    }

    public String toString() {
        String ret =  (mod.equals("") ? "" : (mod + " ")) + typ + " " + name;

        if ( init != null ) {
            ret += " = " + init;
        }

        ret += ";";

        return ret;
    }

    public T init( Expr ini ) {
        this.init = ini;
        return clazz;
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
