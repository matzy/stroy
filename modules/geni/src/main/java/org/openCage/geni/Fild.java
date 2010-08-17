package org.openCage.geni;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 1:10:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fild {
    private String name;
    private Clazz clazz;
    private String mod;
    private Typ typ;
    private String init;

    public Fild( Clazz clazz, String mod, Typ typ, String name) {
        this.name = name;
        this.clazz = clazz;
        this.mod = mod;
        this.typ = typ;
    }

    public Clazz c() {
        return clazz;
    }

    public String toString() {
        String ret =  "   " + mod + " " + typ + " " + name;

        if ( init != null ) {
            ret += " = " + init;
        }

        return ret + ";\n";
    }

    public Fild init( String ini ) {
        this.init = ini;
        return this;
    }
}
