package org.openCage.osashosa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jul 23, 2010
 * Time: 7:51:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class LazyL {

    public static interface A {
        void hi();
        void fup();
    }

    public static class AImpl implements A {

        public AImpl() {
            System.out.println("created");
        }

        @Override
        public void hi() {
            System.out.println("from AImpl");
        }

        @Override
        public void fup() {
            System.out.println("from AImpl fup");
        }
    }

    public static class LazyB<T> implements InvocationHandler {

        private T a;

        public LazyB( Class<T> t) {
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            if ( a == null ) {
                a = (T)new AImpl();
            }
            System.out.println("wooo");
            return method.invoke( a, objects );
        }
    }


    public static void main(String[] args) {
        A a = (A)Proxy.newProxyInstance( A.class.getClassLoader(),
                                     new Class[] { A.class },
                                    new LazyB( A.class ) );

        System.out.println("got a proxy, now lets use it");

        a.hi();
        a.fup();
    }
}
