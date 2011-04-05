package org.openCage.lang.functions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Lx<R, X> implements F1<R, X> {

    public X x;
    private R r;
//    public boolean first;


    public Lx( X x) {
  //      this.first = ff;
        this.x = x;
    }

    public R call(X x) {
        Class<? extends Lx> clazz = this.getClass();

        Constructor<Lx> constructor = (Constructor<Lx>) clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        try {
            return (R)constructor.newInstance( new Object[]{null, x} ).r;


        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        throw new Error("err");
    }

    public Object return_( R r ) {
        this.r = r;
        return null;
    }
}
