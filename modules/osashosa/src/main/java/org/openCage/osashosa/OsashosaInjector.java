package org.openCage.osashosa;

import com.google.inject.BindingBuilder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.structure.ESet;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class OsashosaInjector implements Injector {

    private ESet<BindingBuilder<?>> bindings = new ESet<BindingBuilder<?>>();

    public OsashosaInjector( Module ... modules ) {
        OsashosaBinder binder = new OsashosaBinder();
        for ( Module module : modules ) {
            binder.setCurrentModuleName( module.getClass().getName() );
            module.configure( binder );
        }

        for (BindingBuilder builder : binder.getBuilders() ) {

            if ( bindings.contains( builder )) {
                throw new IllegalStateException( "binding exits already (old new) " + bindings.get( builder) + " " + builder );
            }

            bindings.add( builder );
        }

    }


    public <T> T getInstance( Class<T> clazz ) {
        return getInstance( "", clazz );
    }

    private <T> T getInstance(String name, Class<T> clazz ) {
        return getInstance( name, Key.get(clazz));
    }

    private <T> T getInstance( Key<T> key ) {
        return getInstance( "", key );
    }

    private <T> T getInstance(String name, Key<T> key) {

        BindingBuilder<T> bb = (BindingBuilder<T>)bindings.get( new BindingBuilder(null, key).annotatedWith( Names.named(name)));

        if ( bb == null ) {

            Class clazz = key.getLiteral().getRawType();

            if ( clazz.isInterface() ) {
                throw new UnsupportedOperationException( "no binding for " + key  + ((name == null | name.isEmpty() ) ? "" : ("@Named " + name)  ));
            }

            if ( name != null && name.length() > 0 ) {
                throw new UnsupportedOperationException( "no binding for " + key  + ((name == null | name.isEmpty() ) ? "" : ("@Named " + name)  ));
            }

            // try self binding
            bb = new BindingBuilder( null, key ).to( clazz );
        }

        if ( bb.getSingleton() != null ) {
            return bb.getSingleton();
        }

        Constructor cnstr = getConstructor( bb.isDirect() ? bb.getTo() : bb.getProvider() );
        Object[] params = findArguments(cnstr);
        try {
            Object ret = cnstr.newInstance( params );

            T tret = (bb.isDirect()) ? (T)ret : ((Provider<T>)ret).get();

            if ( bb.isSingletonScope()) {
                bb.setSingleton( tret );
            }

            return tret;

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

    }


    private Constructor getConstructor( Class clazz ) {
        Constructor[] cnstrs = clazz.getConstructors();

        for ( Constructor cc : cnstrs ) {
            if ( cc.isAnnotationPresent( Inject.class )) {
                return cc;
            }
        }

        if ( cnstrs.length == 1 ) { // TODO check argument length as GUICE does
            // find other @Injects

            Field[] fields = clazz.getFields();
            for ( Field field : fields) {

                if ( field.getAnnotation( Inject.class ) != null ) {
                        throw new UnsupportedOperationException( "no method or field @Inject supported, class: " + clazz.getName() );
                }
//                Annotation[] declared = field.getDeclaredAnnotations();
//
//                for ( Annotation anno : clazz.getDeclaredAnnotations() ) {
//                    if ( anno.annotationType().equals( Inject.class )) {
//                        throw new UnsupportedOperationException( "no method or field @Inject supported" );
//                    }
//                }
            }

            return cnstrs[0];
        }

        throw new UnsupportedOperationException( "class " + clazz.getName() + " has no constructor annotated with @Inject" );
    }

    private Object[] findArguments(Constructor cnstr ) {


        Type[] paras = cnstr.getGenericParameterTypes();  //getParameterTypes();
        Object[] pp = new Object[cnstr.getParameterTypes().length];
        Annotation[][] anno = cnstr.getParameterAnnotations();

        for ( int i = 0; i < cnstr.getParameterTypes().length; ++i ) {
            String name = getAnnotatedName( anno[i]);
            if ( name == null ) {
                name = "";
            }

            try {
                pp[i] = getInstance( name, Key.get(paras[i]));
            } catch ( UnsupportedOperationException ex ) {
                throw new UnsupportedOperationException( "Constructor " + cnstr.getName() + " can't be called because " + ex.getMessage() );
            }
        }

        return pp;
    }


    private String getAnnotatedName(Annotation[] annotations ) {
        for ( Annotation ann : annotations) {
            if ( ann.annotationType().equals( Named.class )) {
                return ((Named)ann).value();
            }
        }

        return null;
    }


}
