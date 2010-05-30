package org.openCage.lang.structure;

public final class Tu {

    private Tu() {};

    public static <A,B> T2<A,B> c( A a, B b ) {
        return new T2<A,B>(a,b);
    }
}
