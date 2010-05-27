package org.openCage.property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 27, 2010
 * Time: 11:33:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyConnector {

    public static <T> Property<T> get( PropStore store, String key, T dflt, String descr ) {

        // this should only get a property with a different T
        // if the xstream does not match this implementation
        @SuppressWarnings({"unchecked"}) PropertyImpl<T> prop = (PropertyImpl<T>)store.get(key);

        if ( prop != null ) {
            prop.setStore( store );
            return prop;
        }

        prop = new PropertyImpl<T>( store, dflt, descr );
        store.setProperty( key, prop );
        return prop;
    }


}
