package org.openCage.lang;

import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;

public class Listeners<T> implements ListenerControl<T>{

    private List<F1<Void,T>> listeners = new ArrayList<F1<Void,T>>();

    public void shout( T news ) {
        for ( F1<Void,T> listener : listeners  ) {
            listener.call( news );
        }
    }

    public void add( F1<Void,T> listener ) {
        listeners.add( listener );
    }

    public ListenerControl<T> get() {
        return this;
    }
}
