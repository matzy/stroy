package org.openCage.lang;

import org.openCage.lang.functions.F1;

public interface ListenerControl<T> {

    public void add( F1<Void,T> listener );

}
