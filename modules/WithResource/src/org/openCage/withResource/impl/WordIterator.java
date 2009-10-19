package org.openCage.withResource.impl;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 17, 2009
 * Time: 10:15:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordIterator implements Iterable<String>, Iterator<String>{

    private final String str;
    private int pos = 0;

    public WordIterator( String str ) {
        this.str = str;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return str.indexOf( " ", pos + 1) > -1;
    }

    @Override
    public String next() {
        int next = str.indexOf( " ", pos + 1);
        return str.substring(0,0);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("");
    }
}
