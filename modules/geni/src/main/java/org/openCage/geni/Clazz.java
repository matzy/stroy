package org.openCage.geni;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    private Typ name;
    private String packag;
    List<Mesod> mesods = new ArrayList<Mesod>();

    public List<Fild> filds = new ArrayList<Fild>();

    public Clazz( String packag, Typ name ) {
        this.name = name;
        this.packag = packag;
    }

    public String toString() {
        String ret = "package " + packag + ";\n\n" +
               "public class " + name + " {\n";

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

    public Modi privt() {
        return new Modi( "private",  this );
    }

}
