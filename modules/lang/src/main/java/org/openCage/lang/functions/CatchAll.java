package org.openCage.lang.functions;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class CatchAll {
    private static final Logger LOG = Logger.getLogger( CatchAll.class.getName() );

    private CatchAll() {};

    public static void call( FV f ) {
        try {
            f.call();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }
    }

    public static <R> R call( F0<R> f ) {
        try {
            return f.call();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }

        return null;
    }

    public static <R,A> R call( F1<R,A> f, A a ) {
        try {
            return f.call(a);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }

        return null;
    }

    public static <R,A,B> R call( F2<R,A,B> f, A a, B b ) {
        try {
            return f.call( a, b);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }

        return null;
    }
}
