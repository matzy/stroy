package org.openCage.withResource.impl;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 18, 2009
 * Time: 10:39:39 AM
 * To change this template use File | Settings | File Templates.
 */
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
        throw new Error( "strings are imutable" );
    }

    public Iterator<Character> iterator() {
        return this;
    }
}
