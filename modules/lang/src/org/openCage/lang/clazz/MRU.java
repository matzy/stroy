package org.openCage.lang.clazz;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.annotations.HiddenCall;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ThreadSafe
public class MRU<T> {
    private static final int INIT_SIZE = 10;

    private transient int maxSize = INIT_SIZE;
    private List<T> used = new ArrayList<T>();

    public synchronized Collection<T> getAll() {
        return Collections.unmodifiableCollection(used);
    }

    public synchronized void use( T t ) {
        used.remove( t );
        List<T> newUsed = new ArrayList<T>();
        newUsed.add( t );
        newUsed.addAll( used );
        while( used.size() > maxSize ) {
            used.remove( maxSize );
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

    public synchronized void clear() {
        used.clear();
    }

    @SuppressWarnings({"RedundantThrowsDeclaration"})
    @HiddenCall
 	private Object readResolve() throws ObjectStreamException {
        maxSize = Math.max(INIT_SIZE, used.size());
 		return this;
 	}



}
