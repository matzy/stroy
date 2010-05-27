package org.openCage.lang.structure;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 27, 2010
 * Time: 10:55:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tu {

    public static <A,B> T2<A,B> c( A a, B b ) {
        return new T2<A,B>(a,b);
    }
}
