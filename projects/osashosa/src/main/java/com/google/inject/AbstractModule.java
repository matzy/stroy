package com.google.inject;

import org.openCage.osashosa.Key;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/11/12
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractModule implements Module {

    Binder binder;

    public final synchronized void configure(Binder builder) {
        //checkState(this.binder == null, "Re-entry is not allowed.");

        this.binder = builder;
        try {
            configure();
        }
        finally {
            this.binder = null;
        }
    }

    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    protected abstract void configure();

    protected <T> BindingBuilder<T> bind(Class<T> clazz) {
        return binder.bind( clazz);
    }

    protected <T> BindingBuilder<T> bind(TypeLiteral<T> literal) {
        return binder.bind( literal );
    }


}
