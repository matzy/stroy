package org.openCage.comphy;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 10:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class RMap extends HashMap<Key,Readable> implements Readable {

    public void put( String key , Readable val ) {
        put( Key.valueOf(key), val );
    }

    public void put( String key, String val ) {
        put( Key.valueOf(key), RString.valueOf(val));
    }

    public Readable get( String key ) {
        return get( Key.valueOf(key));
    }
}
