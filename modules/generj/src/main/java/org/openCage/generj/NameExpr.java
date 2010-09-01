package org.openCage.generj;

import org.openCage.lang.Strings;


public class NameExpr implements Callble {
    private String str;

    public NameExpr( String str ) {
        this.str = str;
    }

    public String toString() {
        return str;
    }

    public static NameExpr n( String str ) {
        return new NameExpr( str );
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static NameExpr NAME( String name ) {
        return new NameExpr( name );
    }

    public static NameExpr SETTER( String name ) {
        return new NameExpr( "set" + Strings.toFirstUpper( name ));
    }

    public static NameExpr GETTER( String name ) {
        return new NameExpr( "get" + Strings.toFirstUpper( name ));
    }

    public static NameExpr NULL = new NameExpr( "null" );

}
