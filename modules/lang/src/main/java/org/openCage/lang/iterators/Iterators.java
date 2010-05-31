package org.openCage.lang.iterators;

import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.Count;

import java.io.BufferedReader;
import java.util.Arrays;

public final class Iterators {

    private Iterators() {};

    public static String[] words( String str ) {
        return str.split("( |\t|\r|\n)+");
    }

    public static Iterable<Character> chars( String str ) {
        return new StringIterator(str);
    }

    public static String[] lines( String str ) {
        return str.split( "(\n|\r)+");
    }

    public static LineReaderIterator lines( BufferedReader reader ) {
        return new LineReaderIterator( reader );
    }

    public static <A,B> Parallel<A,B> parallel( Iterable<A> ia, Iterable<B> ib ) {
        return new Parallel<A,B>(ia,ib);
    }

    public static <A,B> Parallel<A,B> parallel( A[] ia, Iterable<B> ib ) {
        return new Parallel<A,B>( Arrays.asList(ia),ib);
    }

    public static <A> Count<A> count( Iterable<A> it ) {
        return Count.count( it );
    }

    public static <A> ArrayCount<A> count( A[] it ) {
        return new ArrayCount( it );
    }

}
