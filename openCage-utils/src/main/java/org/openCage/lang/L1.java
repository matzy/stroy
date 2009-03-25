package org.openCage.lang;

import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Mar 24, 2009
 * Time: 2:02:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class L1<R, A> {

    private R              obj;
    private E1<R,A>        func;

    public L1( @NotNull E1<R,A> func ) {
        this.func = func;
    }

    public R get( E0<A> f) {
        if ( func != null ) {
            try {
                obj = func.c( f.c() );
            } catch (Exception e) {
                // TODO
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            func = null;
        }

        return obj;
    }

}
