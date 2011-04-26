package org.openCage.generj;


import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Typ.TYP;

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

public class Class_ implements ClassI {
    Typ name;
    private Package package_;
    List<Method_> methods = new ArrayList<Method_>();

    List<Field> fields = new ArrayList<Field>();
    private List<String> imports = new ArrayList<String>();

    private List<Typ> extnding = new ArrayList<Typ>();

    List<Class_> innerClazzes = new ArrayList<Class_>();
    private Class_ mother;

    private String modi = "public";
    private List<Typ> implmets = new ArrayList<Typ>();
    private BlockComment comment;

    public Class_( Package aPackage, Typ name) {
        this.name = name;
        this.package_ = aPackage;
    }

    public Class_(Class_ mother, Typ name) {
        this.mother = mother;
        this.name = name;
    }

    public String toString() {
        return toString("");
    }

    public String toString( String prefix ) {
        String ret = "";

        if ( package_ != null ) {
            ret += prefix + "package " + package_ + ";\n\n";
        }

        for ( String imp : imports ) {
            ret += prefix + "import " + imp + ";\n";
        }

        ret += "\n";

        if ( comment != null ) {
            ret += comment.toString( prefix );
        }

        ret += prefix + modi + " class " + name;
        ret += Strings.join( extnding ).prefix( " extends " );
        ret += Strings.join( implmets ).prefix( " implements " );
        ret+= " {\n";

        for ( Class_ inner : innerClazzes ) {
            ret += inner.toString( prefix + "   ");
        }

        for ( Field fld : fields) {
            ret += fld.toString( prefix + "   ") + "\n";
        }

        for ( Method_ method : methods) {
            ret += method.toString( prefix + "   ") + "\n";
        }

        return ret += "\n" + prefix + "}\n";

    }


    public Modi public_() {
        return new Modi( "public",  this );
    }

    @Deprecated
    public Modi privat_() {
        return new Modi( "private",  this );
    }

    public Modi private_() {
        return new Modi( "private",  this );
    }

    @Deprecated
    public Modi packagePrivat() {
        return new Modi( "",  this );
    }

    public Modi packagePrivate() {
        return new Modi( "",  this );
    }


    public Class_ import_(String imp) {
        imports.add( imp );
        return this;
    }

    public Typ import_( Package pkg, Typ typ ) {
        imports.add( pkg.toString() + "." + typ );
        return typ;
    }

    public Class_ extends_(Typ typ) {
        this.extnding.add( typ );
        return this;
    }

    public Class_ r() {
        return mother;
    }

    public Class_ implements_( String name ) {
        return implements_( TYP(name));
    }

    public Class_ implements_(Typ typ) {
        this.implmets.add( typ );
        return this;
    }

    public Class_ property(Typ typ, NameExpr name) {
        String upper = Strings.toFirstUpper( name.toString() );
        private_().field(typ, name );
        public_().method( typ, "get" + upper ).body().return_(name);
        public_().method( "set" + upper ).arg( typ, name ).body().assign( DOT( NAME( "this"), name ), name );
        return this;
    }

    public Class_ property(Typ typ, NameExpr name, Expr init ) {
        String upper = Strings.toFirstUpper( name.toString() );
        private_().field(typ, name ).init( init );
        public_().method( typ, "get" + upper ).body().return_(name);
        public_().method( "set" + upper ).arg( typ, name ).body().assign( DOT( NAME( "this"), name ), name );
        return this;
    }

    public Class_ comment( String ... lines ) {
        comment = new BlockComment( lines );
        return this;
    }

    public Class_ mpl( String author, String email, String time, String project ) {
        comment = new MPL( author, email, time, project ).getComment();
        return this;
    }

    @Override
    public ClassI comment(BlockComment comment) {
        this.comment = comment;
        return this;
    }

    public Class_ setModi(String mod) {
        this.modi = mod;
        return this;
    }
}
