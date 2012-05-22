package org.openCage.io.fspath;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ElementRules {

    private ElementRules() {}

    private static final Pattern legal = Pattern.compile( "([a-z]| |[A-Z]|\\-|_|\\.|[0-9]|\\+)+" );

    public static boolean isLegal( String pathElement ) {
        return legal.matcher( pathElement ).matches(); 
    }

    public static void check( String ... els  ) {
        check(Arrays.asList( els ));
    }

    public static void check( List<String> els  ) {
        for ( String element : els ) {
            if ( element == null ) {
                throw new IllegalArgumentException( "not a legal path element: null" );
            }
            if ( !element.trim().isEmpty() ) {
                if ( !ElementRules.isLegal( element )) {
                    throw new IllegalArgumentException( "not a legal path element: " + element );
                }
            }
        }
    }
}
