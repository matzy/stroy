package org.openCage.osashosa;

import com.google.inject.Key;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

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

public class FactoryImpl implements Factory, Factory2 {
    private Type[] types;
    private OsashosaInjector osashosaInjector;

    public FactoryImpl(OsashosaInjector osashosaInjector, Type[] types) {
        this.types = types;
        this.osashosaInjector = osashosaInjector;
    }

    @Override
    public Object get(Object o) {
        Type t = types[0];
        Class c = Key.get(t).getLiteral().getRawType();
        Constructor cnstr =  osashosaInjector.getConstructor( c );
        Object[] params = findArguments( null, cnstr, o );
        try {
            Object ret = cnstr.newInstance( params );

//            T tret = (bindingBuilder.isDirect()) ? (T)ret : ((Provider<T>)ret).get();
//
//            if ( bindingBuilder.isSingletonScope()) {
//                bindingBuilder.setSingleton( tret );
//            }
//
            return ret;

        } catch (InstantiationException e) {
            e.printStackTrace();
            throw Unchecked.wrap( e );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw Unchecked.wrap( e );
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw Unchecked.wrap( e );
        } catch ( IllegalArgumentException e ) {
            e.printStackTrace();
            throw new IllegalArgumentException( "bound a provider with to " + e );
        }
        //return null;
    }

    private Object[] findArguments( StackList before, Constructor cnstr, Object direct ) {


        Type[] paras = cnstr.getGenericParameterTypes();  //getParameterTypes();
        Object[] pp = new Object[cnstr.getParameterTypes().length];
        Annotation[][] anno = cnstr.getParameterAnnotations();

        for ( int i = 0; i < cnstr.getParameterTypes().length; ++i ) {

            //System.out.println( "" + i + " " + pp[i] );

            if ( i == 0 ) {
                pp[i] = direct;
            } else {

            String name = osashosaInjector.getAnnotatedName( anno[i]);
            if ( name == null ) {
                name = "";
            }

            try {
                Key next = Key.get(paras[i]);
                pp[i] = osashosaInjector.getInstance( before, name, next );
            } catch ( UnsupportedOperationException ex ) {
                throw new UnsupportedOperationException( "Constructor " + cnstr.getName() + " can't be called because " + ex.getMessage() );
            }
            }
        }

        return pp;
    }

    @Override
    public Object get(Object o, Object o1) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
