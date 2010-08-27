package org.openCage.generj;


import com.sun.jndi.dns.DnsName;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
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

        ret += prefix + modi + " class " + name;
        ret += Strings.join( extnding ).prefix( " extends " );
        ret += Strings.join( implmets ).prefix( " implements " );
        ret+= " {\n";

        for ( Clazz inner : innerClazzes ) {
            ret += inner.toString( prefix + "   ");
        }

        for ( Fild fld : filds ) {
            ret += fld.toString( prefix + "   ") + ";\n";
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

    public Clazz property(Typ typ, String name) {
        String upper = Strings.toFirstUpper( name );
        privt().fild(typ, name );
        publc().method( "get" + upper ).body().retrn( Exp.n(name));
        publc().method( typ, "set" + upper ).arg( typ, name ).body().assign( "this." + name, Exp.n(name));
        return this;
    }

    public Clazz property(Typ typ, String name, Expr init ) {
        String upper = Strings.toFirstUpper( name );
        privt().fild(typ, name ).init( init );
        publc().method( "get" + upper ).body().retrn( Exp.n(name));
        publc().method( typ, "set" + upper ).arg( typ, name ).body().assign( "this." + name, Exp.n(name));
        return this;
    }
}
