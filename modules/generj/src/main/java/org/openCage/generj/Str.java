package org.openCage.generj;


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

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static Str STR( String str ) {
        return new Str( str );
    }
}
