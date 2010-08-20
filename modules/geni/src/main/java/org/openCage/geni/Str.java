package org.openCage.geni;

public class Str implements Expr {
    private String str;

    public Str( String str ) {
        this.str = str;
    }

    public String toString() {
        return "\"" +str + "\"";
    }

    public static Str s( String str ) {
        return new Str( str );
    }
}
