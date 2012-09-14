package org.openCage.kleinod.observe;

import org.openCage.kleinod.lambda.F0;
import org.openCage.kleinod.lambda.VF1;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/3/12
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObservableDetail<T> implements Observable {

    private final VoidListenerControl observers;
    private final F0<T> getter;
    private final VF1<T> setter;

    public ObservableDetail( Observable parent, F0<T> getter, VF1<T> setter) {
        this.getter = getter;
        this.setter = setter;
        observers = parent.getListenerControl();
    }

    @Override
    public VoidListenerControl getListenerControl() {
        return observers;
    }

    public T get() {
        return getter.call();
    }

    public void set( T t ) {
        setter.call( t );
    }

}
