package org.openCage.generj;

import org.openCage.lang.Strings;

import java.util.regex.Pattern;


public class NameExpr implements Callble {

    private static Pattern valid = Pattern.compile( "([a-z]*[A-Z]*)*"); // TODO expand

    private final String str;

    public NameExpr( String str ) {

        if ( !valid.matcher( str ).matches() ) {
            throw new IllegalArgumentException( "not a valid java name " + str );
        }

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
