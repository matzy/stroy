package org.openCage.generj;

import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.structure.T2;

import java.util.ArrayList;
import java.util.List;

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
public class Interf implements ClassI {
    public static class MethodDec {
        private Interf parent;
        private Typ retType;
        private String nme;
        private List<T2<Typ,String>> args = new ArrayList<T2<Typ, String>>();

        public MethodDec( Interf parent, Typ retType, String nme ) {
            this.parent = parent;
            this.retType = retType;
            this.nme = nme;
        }

        public MethodDec arg( Typ typ, String name ) {
            this.args.add( T2.valueOf(typ,name));
            return this;
        }

        public String toString(String prefix) {
            return prefix + "public " + retType + " " + nme + "( " + Strings.join( args ).trans( new F1<String, T2<Typ, String>>() {
                @Override
                public String call(T2<Typ, String> arg) {
                    return arg.i0 + " " + arg.i1;
                }
            }) + " );";
        }
    }

    private String pack;
    private Typ name;
    private List<String> imports = new ArrayList<String>();
    private String modi = "public";
    private List<MethodDec> methods = new ArrayList<MethodDec>();
    private BlockComment comment;




    public Interf(String pack, Typ typ) {
        this.pack = pack;
        this.name = typ;
    }

    public String toString() {
        return toString("");
    }

    public String toString( String prefix ) {
        String ret = prefix;

        if ( pack != null ) {
            ret += prefix + "package " + pack + ";\n\n";
        }

        for ( String imp : imports ) {
            ret += prefix + "import " + imp + ";\n";
        }

        if ( comment != null ) {
            ret += comment.toString( prefix );
        }

        ret += "\n";

        ret += prefix + modi + " interface " + name;
        // TODO extends

        ret += " {\n";

        for( MethodDec md : methods ) {
            ret += md.toString( prefix + "   ") + "\n";
        }

        ret += prefix + "}\n";

        return ret;
    }

    public MethodDec method( Typ retTyp, String name ) {
        MethodDec md = new MethodDec( this, retTyp, name );
        methods.add( md );
        return md;
    }

    public Interf imprt(String imp) {
        imports.add( imp );
        return this;
    }


    @Override
    public ClassI comment(BlockComment comment) {
        this.comment = comment;
        return this;
    }

}
