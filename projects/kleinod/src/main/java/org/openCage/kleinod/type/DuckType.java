package org.openCage.kleinod.type;

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

// ref http://thinking-in-code.blogspot.com/2008/11/duck-typing-in-java-using-dynamic.html

/**
 * Allows "duck typing" or dynamic invocation based on method signature rather
 * than type hierarchy. In other words, rather than checking whether something
 * IS-a duck, check whether it WALKS-like-a duck or QUACKS-like a duck.
 * <p/>
 * To use first use the coerce static method to indicate the object you want to
 * do Duck Typing for, then specify an interface to the to method which you want
 * to coerce the type to, e.g:
 * <p/>
 * public interface Foo {
 * void aMethod();
 * }
 * class Bar {
 * ...
 * public void aMethod() { ... }
 * ...
 * }
 * Bar bar = ...;
 * Foo foo = DuckType.coerce(bar).to(Foo.class);
 * foo.aMethod();
 */
public class DuckType {

    private final Object objectToCoerce;

    private DuckType(Object objectToCoerce) {
        this.objectToCoerce = objectToCoerce;
    }

    private class CoercedProxy implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Method delegateMethod = findMethodBySignature(method);
            assert delegateMethod != null;
            return delegateMethod.invoke(DuckType.this.objectToCoerce, args);
        }
    }

    /**
     * Specify the duck typed object to coerce.
     *
     * @param object the object to coerce
     * @return
     */
    public static DuckType coerce(Object object) {
        return new DuckType(object);
    }

    /**
     * Coerce the Duck Typed object to the given interface providing it
     * implements all the necessary methods.
     *
     * @param
     * @param iface
     * @return an instance of the given interface that wraps the duck typed
     *         class
     * @throws ClassCastException if the object being coerced does not implement
     *                            all the methods in the given interface.
     */
    public <T> T to(Class<T> iface) {
        assert iface.isInterface() : "cannot coerce object to a class, must be an interface";
        if (isA(iface)) {
            return iface.cast(objectToCoerce);
        }
        if (quacksLikeA(iface)) {
            return generateProxy(iface);
        }
        throw new ClassCastException("Could not coerce object of type "
                + objectToCoerce.getClass() + " to " + iface);
    }

    private boolean isA(Class iface) {
        return objectToCoerce.getClass().isInstance(iface);
    }

    /**
     * Determine whether the duck typed object can be used with
     * the given interface.
     * <p/>
     * Type of the interface to check.
     *
     * @param iface Interface class to check
     * @return true if the object will support all the methods in the
     *         interface, false otherwise.
     */
    public boolean quacksLikeA(Class iface) {
        for (Method method : iface.getMethods()) {
            if (findMethodBySignature(method) == null) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <T> T generateProxy(Class<T> iface) {
        return (T) Proxy.newProxyInstance(iface.getClassLoader(),
                new Class[]{iface},
                new CoercedProxy());
    }

    private Method findMethodBySignature(Method method) {
        try {
            return objectToCoerce.getClass().getMethod(
                    method.getName(),
                    method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
