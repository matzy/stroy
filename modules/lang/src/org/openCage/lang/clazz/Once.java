package org.openCage.lang.clazz;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 05-abr-2010
 * Time: 0:50:06
 * To change this template use File | Settings | File Templates.
 */
public class Once<T> {

    private T val;
    private boolean set = false;

    public Once( T deflt ) {
        val = deflt;
    }

    public synchronized T get() {
        set = true;
        return val;
    }

    public synchronized void set( T t ) {
        if ( set ) {
            throw new IllegalStateException("can't set Once twice");
        }

        set = true;
        val = t;
    }
}
