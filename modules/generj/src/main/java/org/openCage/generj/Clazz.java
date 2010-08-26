package org.openCage.generj;


import com.sun.jndi.dns.DnsName;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    private Typ name;
    private String packag;
    List<Mesod> mesods = new ArrayList<Mesod>();

    public List<Fild> filds = new ArrayList<Fild>();
    private List<String> imports = new ArrayList<String>();

    private List<Typ> extnding = new ArrayList<Typ>();

    private List<Clazz> innerClazzes = new ArrayList<Clazz>();

    public Clazz( String packag, Typ name ) {
        this.name = name;
        this.packag = packag;
    }

    public Clazz( Typ name ) {
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

        ret += prefix + "public class " + name;
        ret += Strings.join( extnding ).prefix( " extends " );
        ret+= " {\n";

        for ( Clazz inner : innerClazzes ) {
            ret += inner.toString( prefix + "   ");
        }

        for ( Fild fld : filds ) {
            ret += fld.toString( prefix + "   ");
        }

        for ( Mesod mesod : mesods ) {
            ret += mesod.toString( prefix + "   ");
        }

        return ret += "\n" + prefix + "}\n";

    }


    public Modi publc() {
        return new Modi( "public",  this );
    }

    public Modi publcStatic() {
        return new Modi( "public static",  this );
    }

    public Modi privt() {
        return new Modi( "private",  this );
    }

    public Clazz imprt(String imp) {
        imports.add( imp );
        return this;
    }

    public Clazz extnds(Typ typ) {
        this.extnding.add( typ );
        return this;
    }

    public Clazz clazz(Typ typ) {
        Clazz subClazz = new Clazz( typ );
        this.innerClazzes.add( subClazz );
        return subClazz;
    }
}
