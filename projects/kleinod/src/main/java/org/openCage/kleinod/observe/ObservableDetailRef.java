package org.openCage.kleinod.observe;

import org.openCage.kleinod.text.Strings;
import org.openCage.kleinod.collection.Reference;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.VF0;
import org.openCage.kleinod.lambda.VF2;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/4/12
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObservableDetailRef<T> implements ObservableReference<T>, Observer {
    private final VoidListeners observers = new VoidListeners();
    private final F1<T,Observable> getter;
    private final VF2<Observable,T> setter;
    private final Reference<? extends Observable> parent;

//    public ObservableFloatingDetail( F1<T, Observable> getter, VF2<Observable,T> setter) {
//        this.getter = getter;
//        this.setter = setter;
//    }
//
    public ObservableDetailRef( ObservableReference<? extends Observable> parent, final String member) {

        this.parent = parent;
        parent.getListenerControl().add( new VF0() {
            @Override
            public void call() {
                observers.shout();
            }
        });

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
        if ( parent.get() == null ) {
            return null;
        } else {
            return getter.call(parent.get());
        }
    }

    @Override
    public void set( T t ) {
        if ( parent.get() != null ) {
            setter.call( parent.get(), t );
        }
    }


    @Override
    public void call() {
        observers.shout();
    }

}
