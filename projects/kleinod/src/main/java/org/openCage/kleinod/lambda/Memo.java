package org.openCage.kleinod.lambda;

import org.openCage.kleinod.collection.T2;
import org.openCage.kleinod.type.Null;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/20/12
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Memo<R,T> {

    private static HashMap<T2<Object, F1>, Object> mem = new HashMap<T2<Object, F1>, Object>();

    private F1<R, T> fct;

    public Memo( F1<R,T> fct ) {
        this.fct = fct;
        if (Null.is(fct)) {
            throw new IllegalArgumentException( "Memo does not allow null functions" );
        }
    }

    public R get( T base ) {

        T2<Object,F1> key = T2.valueOf((Object)base,(F1)fct);

        if ( !mem.containsKey( key )) {
            mem.put( key, fct.call(base));
        }

        return (R)mem.get( key );
    }

}
