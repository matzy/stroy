package org.openCage.lang.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 2, 2010
 * Time: 12:39:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class BFStack<T> {

    private List<T> list = new ArrayList<T>();
    private int pos = -1;

    public T forward() {
        if ( !hasForward() ) {
            throw new IllegalStateException( "no forward" );
        }

        pos++;
        return list.get(pos);
    }

    public boolean hasForward() {
        return pos < list.size() - 1;
    }

    public boolean hasBackward() {
        return pos > 0;
    }

    public T backward() {
        if ( !hasBackward() ) {
            throw new IllegalStateException( "no more backward" );
        }
        pos--;
        return list.get(0);
    }

    public void push( T elem ) {
        if ( hasForward() ) {
            list = list.subList( 0, pos + 1);
        }
        list.add( elem );
        pos++;
    }

    public T getCurrent() {
        if ( list.size() == 0 ) {
            throw new IllegalStateException( "nothing in stack yet" );
        }
        return list.get(pos);
    }

    public int size() {
        return list.size();
    }
    
    public void clear() {
        pos = -1;
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
