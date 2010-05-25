package org.openCage.lang.iterators;

import java.io.BufferedReader;

public class Iterators {

    public static String[] words( String str ) {
        return str.split(" ");
    }

    public static Iterable<Character> chars( String str ) {
        return new StringIterator(str);
    }

    public static String[] lines( String str ) {
        return str.split( "\n|\r");
    }

    public static LineReaderIterator lines( BufferedReader reader ) {
        return new LineReaderIterator( reader );
    }
}
