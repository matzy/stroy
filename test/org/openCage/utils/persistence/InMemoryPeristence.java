package org.openCage.utils.persistence;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 30, 2008
 * Time: 2:10:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class InMemoryPeristence<T extends Persistable> implements Persistence<T> {

    private Map<String, T> mem = new HashMap<String,T>();

    public T load( T init, @NotNull String name ) {
        if ( mem.containsKey( name )) {
            return mem.get( name );
        }

        mem.put( name, init );
        
        return mem.get( name );
    }

    public void save( T prefs, @NotNull String name ) {
    }
}
