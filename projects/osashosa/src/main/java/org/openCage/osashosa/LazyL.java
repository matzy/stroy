package org.openCage.osashosa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

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
