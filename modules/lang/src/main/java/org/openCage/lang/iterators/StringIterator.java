package org.openCage.lang.iterators;

import java.util.Iterator;

public class StringIterator implements Iterator<Character>, Iterable<Character> {
    private final String str;
    private int    idx;


    public StringIterator( String str ) {
        this.str = str;
        this.idx = 0;
    }

    public boolean hasNext() {
        return str.length() > idx;
    }

    public Character next() {
        Character ch = str.charAt(idx);
        ++idx;
        return ch;
    }

    public void remove() {
        throw new UnsupportedOperationException( "strings are imutable" );
    }

    public Iterator<Character> iterator() {
        return this;
    }
}
