/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.lang.protocol.tuple;

/**
 *
 * @author stephan
 */
public class T2<A,B> {

    public final A i0;
    public final B i1;

    public T2( final A a, final B b ) {
        this.i0 = a;
        this.i1 = b;
    }

    public static <A,B> T2<A,B> c( A a, B b ) {
        return new T2<A,B>(a,b);
    }
}
