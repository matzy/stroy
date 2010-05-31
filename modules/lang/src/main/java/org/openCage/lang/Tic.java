package org.openCage.lang;

import org.openCage.lang.functions.F0;
import org.openCage.lang.structure.Lazy;
import org.openCage.lang.structure.T2;
import org.openCage.lang.structure.Tu;

import java.util.HashMap;
import java.util.Map;

public final class Tic {

    private Tic() {};

    private static Map<Class, F0>   multi = new HashMap<Class, F0>();
    private static Map<Class, Lazy> singleton = new HashMap<Class, Lazy>();
    private static Map<T2<Class, String>, F0> multiTag = new HashMap<T2<Class, String>, F0>();
    private static Map<T2<Class, String>, Lazy> singletonTag = new HashMap<T2<Class, String>, Lazy>();

    public static <T> T get(Class<T> c) {

        F0<T> f = multi.get( c );
        if ( f != null ) {
            return f.call();
        }

        Lazy<T> l = singleton.get(c);

        if ( l != null ) {
            return l.get();
        }

        throw new IllegalStateException( "no object for class " + c.getName() );
    }

    public static <T> T get(Class<T> c, String tag ) {

        F0<T> f = multiTag.get( Tu.c(c,tag) );
        if ( f != null ) {
            return f.call();
        }

        Lazy<T> l = singletonTag.get( Tu.c(c,tag));

        if ( l != null ) {
            return l.get();
        }

        throw new IllegalStateException( "no object for class " + c.getName() + " with tag " + tag);
    }

    public static <T> void bindSingleton(Class<T> c, F0<T> f ) {
        singleton.put( c, new Lazy<T>( f ) );
    }

    public static <T> void bind(Class<T> c, F0<T> f ) {
        multi.put( c ,f );
    }

    public static <T> void bind(Class<T> c, String tag, F0<T> f ) {
        multiTag.put( Tu.c( (Class)c, tag ), f );
    }

    public static <T> void bindSingleton(Class<T> c, String tag, F0<T> f ) {
        singletonTag.put( Tu.c( (Class)c, tag ), new Lazy<T>(f) );
    }
    public static void clear() {
        multi.clear();
        singleton.clear();
    }

}
