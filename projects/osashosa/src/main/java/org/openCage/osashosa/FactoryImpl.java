package org.openCage.osashosa;

import com.google.inject.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/9/12
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
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
