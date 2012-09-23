package org.openCage.kleinod.observe;

import org.openCage.kleinod.text.Strings;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.VF2;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/3/12
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObservableFloatingDetail<T> implements ObservableReference<T>, Observer  {

    private final VoidListeners observers = new VoidListeners();
    private final F1<T,Observable> getter;
    private final VF2<Observable,T> setter;
    private Observable parent;

    public ObservableFloatingDetail( F1<T, Observable> getter, VF2<Observable,T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public ObservableFloatingDetail( final String member ) {
        final String method = Strings.toFirstUpper(member);
        getter = new F1<T, Observable>() {
            @Override
            public T call(Observable observable) {
                try {
                    return (T) observable.getClass().getDeclaredMethod( "get" + method ).invoke( observable );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                return null;
            }
        };

        setter = new VF2<Observable, T>() {
            @Override
            public void call(Observable observable, T t) {
                try {
                    observable.getClass().getDeclaredMethod( "set" + method, t.getClass() ).invoke( observable, t );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };
    }

        @Override
    public VoidListenerControl getListenerControl() {
        return observers;
    }

    @Override
    public T get() {
        if ( parent == null ) {
            return null;
        } else {
            return getter.call(parent);
        }
    }

    @Override
    public void set( T t ) {
        if ( parent != null ) {
            setter.call( parent, t );
        }
    }

    public void setBase( Observable ob ) {
        if ( parent != null ) {parent.getListenerControl().remove( this );}

        ob.getListenerControl().add(this);
        parent = ob;
        observers.shout();
    }

    @Override
    public void call() {
        observers.shout();
    }
}
