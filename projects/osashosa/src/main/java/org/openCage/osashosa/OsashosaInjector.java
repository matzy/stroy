package org.openCage.osashosa;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

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

public class OsashosaInjector implements Injector {

    private ESet<BindingBuilder<?>> bindings = new ESet<BindingBuilder<?>>();
    private final Stage stage;

    public OsashosaInjector( Stage stage, Module ... modules ) {
        this.stage = stage;
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

        createEagerObjects();
    }

    private void createEagerObjects() {
        for ( BindingBuilder binding : bindings ) {
            if ( binding.isEager() ) {
                getInstanceNow( null, binding );
            }
        }
    }


    public <T> T getInstance( Class<T> clazz ) {
        return getInstance( null, "", clazz );
    }

    @Override
    public void injectMembers(Object o) {

        Class clazz = o.getClass();

        for ( Field field : clazz.getFields()) {

            if ( field.getAnnotation( Inject.class ) != null ) {
                Annotation anno = field.getAnnotation( Named.class );
                String name = "";
                if ( anno != null ) {
                    name = ((Named)anno).value();
                }
                try {
                    field.set( o, getInstance( null, name, field.getType()) );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
         //       throw new UnsupportedOperationException( "no method or field @Inject supported, class: " + clazz.getName() );
            }
        }

    }

    private <T> T getInstance( StackList before, String name, Class<T> clazz ) {
        return getInstance( before, name, Key.get(clazz));
    }

    private <T> T getInstance( StackList before, Key<T> key ) {
        return getInstance( before, "", key );
    }

    <T> T getInstance(StackList before, String name, Key<T> key) {

        BindingBuilder<T> bb = (BindingBuilder<T>)bindings.get( (BindingBuilder)new BindingBuilder(null, key).annotatedWith( Names.named(name)));

        if ( bb == null ) {

            Class clazz = key.getLiteral().getRawType();

            if ( clazz.equals( Factory.class )) {
                TypeLiteral<T> lit = key.getLiteral();
                Type typ = lit.getType();
                if ( !(typ instanceof ParameterizedType )) {
                    throw new Error("huh");
                } else {
                    ParameterizedType ptype = (ParameterizedType)typ;
                    Type[] args = ptype.getActualTypeArguments();
                    return (T) new FactoryImpl( this, args );
                }
            }

            if ( clazz.isInterface() ) {
                throw new UnsupportedOperationException( "no binding for " + key  + ((name == null | name.isEmpty() ) ? "" : ("@Named " + name)  ));
            }

            if ( name != null && name.length() > 0 ) {
                throw new UnsupportedOperationException( "no binding for " + key  + ((name == null | name.isEmpty() ) ? "" : ("@Named " + name)  ));
            }

            // try self binding
            bb = (BindingBuilder<T>)new BindingBuilder( null, key ).to( clazz );
        }

        if ( bb.getSingleton() != null ) {
            return bb.getSingleton();
        }


//        // TODO check stage
//        Class raw = key.getLiteral().getRawType();
//        if ( raw.isInterface() ) {
//            return (T)Proxy.newProxyInstance( raw.getClassLoader(),
//                                              new Class[] { raw },
//                                              new LazyProxy( bb, this ));
//        }

        return getInstanceNow( before, bb );
    }

    <T> T getInstanceNow( StackList before, BindingBuilder<T> bindingBuilder ) {
        if ( stage == Stage.DEVELOPMENT ) {
            // not correct yet
            if ( before != null && before.contains( bindingBuilder )) {
                throw new ConfigurationException( "loop in type graph detected: " + new StackList( bindingBuilder, before ) );
            }
            before = new StackList( bindingBuilder, before );
        }

        if ( bindingBuilder.getConcreteProvider() != null ) {
            Provider<? extends T> prov = bindingBuilder.getConcreteProvider();
            injectMembers(prov);
            return prov.get();
        }

        Constructor cnstr = getConstructor( bindingBuilder.isDirect() ? bindingBuilder.getTo() : bindingBuilder.getProvider() );
        Object[] params = findArguments( before, cnstr);
        try {
            Object ret = cnstr.newInstance( params );

            T tret = (bindingBuilder.isDirect()) ? (T)ret : ((Provider<T>)ret).get();

            if ( bindingBuilder.isSingletonScope()) {
                bindingBuilder.setSingleton( tret );
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
            throw new IllegalArgumentException( "bound a provider with 'to', should be 'toProvider' " + e );
        }
    }


    /**
     * find a GUICE constructor
     *   a) the only one annotated with @Inject
     *   b) the only constructor, one without args 
     * @param clazz
     * @return
     */
    public Constructor getConstructor( Class clazz ) {

        throwOnOtherInjects(clazz);

        Constructor ret = getInjectedConstructor(clazz);

        if ( ret != null ) {
            return ret;
        }

        Constructor[] cnstrs = clazz.getConstructors();
        if ( cnstrs.length == 1 ) {

            if ( cnstrs[0].getGenericParameterTypes().length != 0 ) {
                throw new ConfigurationException( "class " + clazz.getName() + " has only a constructor with arguments");
            }

            return cnstrs[0];
        }

        throw new ConfigurationException( "class " + clazz.getName() + " has no constructor annotated with @Inject" );
    }

    private void throwOnOtherInjects(Class clazz) {
        // find other @Injects

        for ( Field field : clazz.getFields()) {

            if ( field.getAnnotation( Inject.class ) != null ) {
                    throw new UnsupportedOperationException( "no method or field @Inject supported, class: " + clazz.getName() );
            }
        }


        for ( Method meth : clazz.getMethods() ) {
            if ( meth.getAnnotation( Inject.class ) != null ) {
                    throw new UnsupportedOperationException( "no method or field @Inject supported, class: " + clazz.getName() );
            }
        }
    }

    private Constructor getInjectedConstructor(Class clazz) {
        Constructor[] cnstrs = clazz.getConstructors();

        Constructor ret = null;

        for ( Constructor cc : cnstrs ) {
            if ( cc.isAnnotationPresent( Inject.class )) {
                if ( ret == null ) {
                    ret = cc;
                } else {
                    throw new ConfigurationException( "class " + clazz.getName() + " as more than one constructor annotated with @Inject");
                }
            }
        }
        return ret;
    }

    private Object[] findArguments( StackList before, Constructor cnstr ) {


        Type[] paras = cnstr.getGenericParameterTypes();  //getParameterTypes();
        Object[] pp = new Object[cnstr.getParameterTypes().length];
        Annotation[][] anno = cnstr.getParameterAnnotations();

        for ( int i = 0; i < cnstr.getParameterTypes().length; ++i ) {

            //System.out.println( "" + i + " " + pp[i] );
            
            String name = getAnnotatedName( anno[i]);
            if ( name == null ) {
                name = "";
            }

            try {
                Key next = Key.get(paras[i]);
                pp[i] = getInstance( before, name, next );
            } catch ( UnsupportedOperationException ex ) {
                throw new UnsupportedOperationException( "Constructor " + cnstr.getName() + " can't be called because " + ex.getMessage() );
            }
        }

        return pp;
    }


    String getAnnotatedName(Annotation[] annotations) {
        for ( Annotation ann : annotations) {
            if ( ann.annotationType().equals( Named.class )) {
                return ((Named)ann).value();
            }
        }

        return null;
    }


}
