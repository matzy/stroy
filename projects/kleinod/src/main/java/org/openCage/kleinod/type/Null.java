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
public class Null {

    public static interface JustNull {
        Class getNullClass();
    }

    public static <T> T of( Class<T> clazz ) {
        if ( !clazz.isInterface()) {
            throw new IllegalArgumentException("only interfaces can be null proxied not " + clazz );
        }

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz, JustNull.class}, new CoercedProxy(clazz));
    }

    public static class CoercedProxy implements InvocationHandler {

        private final Class clazz;

        private CoercedProxy(Class clazz) {
            this.clazz = clazz;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            String methodName = method.getName();

            if (methodName.equals("getNullClass") && (args == null || args.length == 0)) {
                return clazz;
            }

            // it delegates the hashCode and the equals methods to the proxy class
            if (methodName.equals("hashCode") && (args == null || args.length == 0)) {
                return clazz.hashCode();
            }

            if (methodName.equals("equals") && args.length == 1 && args[0] instanceof JustNull) {
                return ((JustNull)args[0]).getNullClass() == clazz;
            }

            Class retType  = method.getReturnType();

            if ( retType.isInterface() ) {
                return of(retType);
            } else if ( retType.isPrimitive() ) {
                if ( retType.isAssignableFrom( int.class )) {
                    return 0;
                }
                if ( retType.isAssignableFrom( long.class )) {
                    return 0L;
                }
                if ( retType.isAssignableFrom( double.class )) {
                    return 0D;
                }
                if ( retType.isAssignableFrom( float.class )) {
                    return 0F;
                }
                if ( retType.isAssignableFrom( boolean.class )) {
                    return false;
                }
                if ( retType.isAssignableFrom( char.class )) {
                    return ' ';
                }
            }

            if ( retType.isAssignableFrom( String.class )) {
                return "";
            }
            if ( retType.isAssignableFrom( Long.class )) {
                return 0L;
            }
            if ( retType.isAssignableFrom( Integer.class )) {
                return 0;
            }
            if ( retType.isAssignableFrom( Double.class )) {
                return 0D;
            }
            if ( retType.isAssignableFrom( Float.class )) {
                return 0F;
            }
            if ( retType.isAssignableFrom( Character.class )) {
                return ' ';
            }


            return null;
        }
    }

    public static boolean is( Object obj ) {
        return obj == null || obj instanceof JustNull;
    }

    public static boolean isNot( Object obj ) {
        return obj != null && !(obj instanceof JustNull);
    }

    public static <T> T Q( T t ) {
        if ( t != null ) {
            return t;
        }

        return (T) Null.of(t.getClass());
    }

}
