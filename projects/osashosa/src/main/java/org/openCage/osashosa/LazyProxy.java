package org.openCage.osashosa;

import com.google.inject.BindingBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LazyProxy<T> implements InvocationHandler {

    private T                       a;
    private final OsashosaInjector  inj;
    private final BindingBuilder<T> binding;

    public LazyProxy( BindingBuilder<T> binding, OsashosaInjector inj) {
        this.inj = inj;
        this.binding = binding;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        synchronized(this) {
            if ( a == null ) {
                a = inj.getInstanceNow( null, binding );
            }
        }
        return method.invoke( a, objects );
    }
}
