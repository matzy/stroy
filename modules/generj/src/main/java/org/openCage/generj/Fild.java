package org.openCage.generj;

public class Fild<T> implements Statement {
    private String name;
    private T clazz;
    private String mod;
    private Typ typ;
    private Expr init;

    public Fild( T clazz, String mod, Typ typ, String name) {
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
