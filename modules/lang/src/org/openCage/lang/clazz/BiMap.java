package org.openCage.lang.clazz;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 31, 2010
 * Time: 1:56:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BiMap<A,B> {

    private Map<A, B> forward = new HashMap<A,B>();
    private Map<B, A> backward = new HashMap<B,A>();

    public void put( A a, B b ) {
        forward.put(a,b);
        backward.put(b,a);
    }

    public B get( A a ) {
        return forward.get(a);
    }

    public A getReverse( B b ) {
        return backward.get(b);
    }

    public Collection<A> keys() {
        return forward.keySet();
    }

    public Collection<B> vals() {
        return forward.values();
    }
}
