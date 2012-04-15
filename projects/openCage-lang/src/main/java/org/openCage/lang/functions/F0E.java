package org.openCage.lang.functions;

import org.openCage.lang.errors.Unchecked;

public abstract class F0E<T> implements F0<T>{

    @Override public T call() {
        try {
            return callWithException();
        } catch (Exception e) {
            throw new Unchecked( e );
        }
    }
    
    public abstract T callWithException() throws Exception;
}
