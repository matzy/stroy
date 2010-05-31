package org.openCage.io.fspath;

import java.util.regex.Pattern;

public class ElementRules {

    private static Pattern legal = Pattern.compile( "([a-z]|[A-Z]|\\-|_|\\.|[0-9]|\\+)+" );

    public static boolean isLegal( String pathElement ) {
        return legal.matcher( pathElement ).matches(); 
    }

    public static void check( String ... els  ) {
        for ( String element : els ) {
            if ( !element.trim().isEmpty() ) {
                if ( !ElementRules.isLegal( element )) {
                    throw new IllegalArgumentException( "no a legal path element: " + element );
                }
            }
        }
    }
}
