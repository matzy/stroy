package org.openCage.geni;

import com.sun.jndi.dns.DnsName;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    private Typ name;
    private String packag;
    List<Mesod> mesods = new ArrayList<Mesod>();

    public List<Fild> filds = new ArrayList<Fild>();
    private List<String> imports = new ArrayList<String>();

    public Clazz( String packag, Typ name ) {
        this.name = name;
        this.packag = packag;
    }

    public String toString() {
        String ret = "package " + packag + ";\n\n";

        for ( String imp : imports ) {
            ret += "import " + imp + ";\n";
        }

        ret += "public class " + name + " {\n";

        for ( Fild fld : filds ) {
            ret += fld.toString();
        }

        for ( Mesod mesod : mesods ) {
            ret += mesod.toString();
        }

        return ret += "}\n";
    }

//    public Mesod method( String retType, String name ) {
//        Mesod mes = new Mesod( this, retType, name );
//
//        mesods.add( mes );
//
//        return mes;
//    }
    
//    public Clazz field( String typ, String name ) {
//        filds.a
//
//        return this;
//    }

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
}
