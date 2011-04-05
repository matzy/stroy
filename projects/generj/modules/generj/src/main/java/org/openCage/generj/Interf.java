package org.openCage.generj;

import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
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

    public Interf _import(String imp) {
        imports.add( imp );
        return this;
    }


    @Override
    public ClassI comment(BlockComment comment) {
        this.comment = comment;
        return this;
    }

}
