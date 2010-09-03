package org.openCage.generj;


import com.sun.jndi.dns.DnsName;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.Dot.DOT;
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
public class Clazz implements ClassI {
    Typ name;
    private String packag;
    List<Mesod> mesods = new ArrayList<Mesod>();

    List<Fild> filds = new ArrayList<Fild>();
    private List<String> imports = new ArrayList<String>();

    private List<Typ> extnding = new ArrayList<Typ>();

    List<Clazz> innerClazzes = new ArrayList<Clazz>();
    private Clazz mother;

    private String modi = "public";
    private List<Typ> implmets = new ArrayList<Typ>();
    private BlockComment comment;

    public Clazz( String packag, Typ name ) {
        this.name = name;
        this.packag = packag;
    }

    public Clazz( Clazz mother, Typ name ) {
        this.mother = mother;
        this.name = name;
    }

    public String toString() {
        return toString("");
    }

    public String toString( String prefix ) {
        String ret = "";

        if ( packag != null ) {
            ret += prefix + "package " + packag + ";\n\n";
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

        for ( Clazz inner : innerClazzes ) {
            ret += inner.toString( prefix + "   ");
        }

        for ( Fild fld : filds ) {
            ret += fld.toString( prefix + "   ") + "\n";
        }

        for ( Mesod mesod : mesods ) {
            ret += mesod.toString( prefix + "   ") + "\n";
        }

        return ret += "\n" + prefix + "}\n";

    }


    public Modi publc() {
        return new Modi( "public",  this );
    }

    public Modi privt() {
        return new Modi( "private",  this );
    }

    public Modi packagePrvt() {
        return new Modi( "",  this );
    }


    public Clazz imprt(String imp) {
        imports.add( imp );
        return this;
    }

    public Clazz extnds(Typ typ) {
        this.extnding.add( typ );
        return this;
    }

    public Clazz r() {
        return mother;
    }

    public Clazz implments(Typ typ) {
        this.implmets.add( typ );
        return this;
    }

    public Clazz property(Typ typ, NameExpr name) {
        String upper = Strings.toFirstUpper( name.toString() );
        privt().fild(typ, name );
        publc().method( typ, "get" + upper ).body().retrn( name);
        publc().method( "set" + upper ).arg( typ, name ).body().assign( DOT( NAME( "this"), name ), name );
        return this;
    }

    public Clazz property(Typ typ, NameExpr name, Expr init ) {
        String upper = Strings.toFirstUpper( name.toString() );
        privt().fild(typ, name ).init( init );
        publc().method( typ, "get" + upper ).body().retrn( name );
        publc().method( "set" + upper ).arg( typ, name ).body().assign( DOT( NAME( "this"), name ), name );
        return this;
    }

    public Clazz comment( String ... lines ) {
        comment = new BlockComment( lines );
        return this;
    }

    public Clazz mpl( String author, String email, String time, String project ) {
        comment = new MPL( author, email, time, project ).getComment();
        return this;
    }

    @Override
    public ClassI comment(BlockComment comment) {
        this.comment = comment;
        return this;
    }
}
