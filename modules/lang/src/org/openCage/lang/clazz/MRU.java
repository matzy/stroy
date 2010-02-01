package org.openCage.lang.clazz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MRU<T> {
    private int maxSize = 10;
    private List<T> used = new ArrayList<T>();

    public synchronized  T getTop() {
        return used.get(0);
    }

    public synchronized Collection<T> getAll() {
        return Collections.<T>unmodifiableCollection(used);
    }

    public synchronized void use( T t ) {
        used.remove( t );
        List<T> newUsed = new ArrayList();
        newUsed.add( t );
        newUsed.addAll( used );
        if ( newUsed.size() > maxSize ) {
            newUsed.remove( maxSize );
        }
        used = newUsed;
    }

    public synchronized void setMaxSize( int size ) {

        if ( size < 1 ) {
            throw new IllegalArgumentException( "MRU size must be at least 1 not: " + size );
        }

        this.maxSize = size;
        while( used.size() > maxSize ) {
            used.remove( maxSize );
        }
    }


}
