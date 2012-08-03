package org.openCage.ruleofthree.jtothree;

import com.google.inject.ConfigurationException;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import org.openCage.lang.functions.F1;
import org.openCage.lang.inc.Null;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeHashMap;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.ThreeMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class JfromThree {

    private Basics basics = Basics.get();

    public JfromThree() {
    }

    public <T> T get(TypeLiteral<? extends T> typeLiteral, Three three) {
        if ( three == null ) {
            throw new IllegalArgumentException("bad key (?)");
        }

        Class clazz = typeLiteral.getRawType();

        F1<T,Three> basic = basics.getFromThree(clazz);
        if ( basic != null ) {
            return basic.call(three);
        }

        if (ThreeMap.class.isAssignableFrom(clazz)) {
            if ( !three.isMap() ) {
                throw new IllegalArgumentException( "expected an three newMap not: " + three );
            }
            return getThreeMap(typeLiteral, three.getMap());
        }

        if ( Map.class.isAssignableFrom( clazz )) {
            if ( !three.isList() ) {
                throw new IllegalArgumentException( "expected an three List of lists not: " + three );
            }
            return getOtherMap(typeLiteral, three.getList());
        }

        if ( List.class.isAssignableFrom( clazz )) {
            return getList(typeLiteral, three, clazz);
        }

        //enum

        if ( clazz.isEnum()) {
            return getEnum( three, clazz);
        }

        // other object (user defined)
        Constructor cnstr = getConstructor( clazz );
        Object[]    args  = findArguments( typeLiteral, cnstr, three );

        try {
            return (T)cnstr.newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        throw new Error("huh");
    }

    private <T> T getEnum( Three three, Class clazz) {
        if ( !three.isString()) {
            throw new IllegalArgumentException("can't build enum from " + three);
        }

        String name = three.getString();
        for ( Object en : clazz.getEnumConstants()) {
            if ( en.toString().equals(name)) {
                return (T)en;
            }

        }
        throw new IllegalArgumentException( "can't convert " +  name + " to " + clazz);
    }

    private <T> T getImmutableProperty(Three readable, Class clazz) {
//        Constructor[] cnstrs = clazz.getConstructors();
//
//        if ( cnstrs.length != 2 ) {
//            throw new IllegalStateException( "A ImmutableProperty must have a default constructor: " + clazz );
//        }
//
//        Constructor dflt = cnstrs[0];
//        if ( dflt.getGenericParameterTypes().length != 0 ) {
//            dflt = cnstrs[1];
//        }
//
//        if ( dflt.getGenericParameterTypes().length != 0 ) {
//            throw new Error("huh");
//        }
//
//
//        try {
//            AbstractImmutableProperty prop = (AbstractImmutableProperty)dflt.newInstance();
//            Object elem = get(prop.get().getClass(),readable);
//            prop.set(elem);
//            return (T)prop;
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch ( IllegalArgumentException e ) {
//            e.printStackTrace();
//            int t = 0;
//        }

        throw new Error("oops");
    }

    private <T> T getImmuProp(Three readable, TypeLiteral literal ) {
//        Constructor[] cnstrs = literal.getRawType().getConstructors();
//
//        if ( cnstrs.length != 1 ) {
//            throw new IllegalStateException( "A ImmutableProperty must have a default constructor: " + literal );
//        }
//
//        Constructor dflt = cnstrs[0];
//
//        TypeLiteral of = getTypeParameter( literal );
//
////        if ( dflt.getGenericParameterTypes().length != 0 ) {
////            dflt = cnstrs[1];
////        }
////
////        if ( dflt.getGenericParameterTypes().length != 0 ) {
////            throw new Error("huh");
////        }
//
//
//        try {
//            Object   obj  = get( of, readable );
//            ImmuProp prop = (ImmuProp)dflt.newInstance( obj );
//            return (T)prop;
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch ( IllegalArgumentException e ) {
//            e.printStackTrace();
//            int t = 0;
//        }

        throw new Error("oops");
    }


    private <T> T getList(TypeLiteral<T> typeLiteral, Three readable, Class clazz) {
        Constructor cnstr = getConstructor( clazz );

        if ( cnstr == null ) {
            throw new IllegalStateException("no constructor for for tlist derevied class " + clazz );
        }

        try {
            List list = (List)cnstr.newInstance();
            TypeLiteral param = getTypeParameter(typeLiteral);
            if ( readable.isList() ) {
                for ( Three elem : readable.getList() ) {
                    list.add(get(param, elem));
                }
            } else if ( readable.isMap()) {
                if ( readable.getMap().size() == 1 ) {
                    // ok
                    list.add( get( param, readable.getMap().values().iterator().next() ));
                } else if ( readable.getMap().size() > 1 ) {
                    throw new IllegalArgumentException( "list can not be build from maps with more than 1 element" );
                }
            }
            // this accepts maps and strings as empty lists (maps is ok)
            return (T)list;
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        throw new Error("foo");
    }

    private <T> T getMap(TypeLiteral<T> typeLiteral, Class clazz, Map<ThreeKey,Three> threeMap) {


//        if ( !MapProperty.class.isAssignableFrom( clazz )) {
//            throw new IllegalArgumentException("only MapProperty supported for now, not " + clazz);
//        }
//
//        ParameterizedType pa = (ParameterizedType)typeLiteral.getType();
//        Constructor cnstr = getConstructor(clazz);
//        if ( cnstr == null ) {
//            throw new IllegalStateException("no constructor for for tlist derevied class " + clazz );
//        }
//
//        try {
//            MapProperty map = (MapProperty)cnstr.newInstance();
//            Type mapof = pa.getActualTypeArguments()[0];
//            for ( Str key :  readable.keySet()) {
//                map.put(Strng.valueOf(key.get()), (Property)get( Key.get(mapof).getLiteral(), readable.getG(key)));
//            }
//            return (T)map;
//
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        throw new Error("huh");
    }

    private <T> T getThreeMap(TypeLiteral<T> typeLiteral, Map<ThreeKey, Three> threeMap) {

        Constructor cnstr = getConstructor(typeLiteral.getRawType());

        if ( cnstr == null ) {
            throw new IllegalStateException("no constructor for for ThreeMap derevied class " + typeLiteral );
        }

        try {
            ThreeMap ret = (ThreeMap) cnstr.newInstance();
            TypeLiteral para = getTypeParameter(typeLiteral);
            for ( Map.Entry<ThreeKey,Three> entry : threeMap.entrySet() ) {
                ret.put( entry.getKey(), get( para, entry.getValue()));
            }

            return (T) ret;
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return (T) Null.get(ThreeMap.class);

    }

    private <T> T getOtherMap(TypeLiteral<T> typeLiteral, List<Three> threeList) {


        // TODO ReiHashMap
        Constructor cnstr = getConstructor(typeLiteral.getRawType());

        if ( cnstr == null ) {
            throw new IllegalStateException("no constructor for for ThreeMap derevied class " + typeLiteral );
        }

        try {
            Map ret = (Map) cnstr.newInstance();
            TypeLiteral keyType = getTypeParameter(typeLiteral,0);
            TypeLiteral valType = getTypeParameter(typeLiteral,1);
            for ( Three elem : threeList ) {
                if ( !elem.isList() || elem.getList().size() != 2 ) {
                    throw new IllegalArgumentException("expected a list of pairs (list) not: " + threeList );
                }

                Object key = get( keyType, elem.getList().get(0));
                Object val = get( valType, elem.getList().get(1));

                ret.put( key, val );
            }

            return (T) ret;
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return (T) Null.get(ThreeMap.class);

    }

    public <T> T get(Class<T> clazz, Three readable) {

        return get( com.google.inject.Key.get(clazz).getLiteral(),readable);
    }


    private Constructor getConstructor( Class clazz ) {

        if ( clazz.isInterface() ) {
            throw new IllegalArgumentException( "can't inject an interface " + clazz );
        }

        Constructor ret = getInjectedConstructor(clazz);

        if ( ret != null ) {
            return ret;
        }

        // now get the default constructor
        ret = getSingleConstructor(clazz);
        if ( ret != null ) {
            return ret;
        }

        ret = getDefaultConstructor(clazz);
        if ( ret != null ) {
            return ret;
        }

        throw new ConfigurationException( "class " + clazz.getName() + " has no constructor annotated with @Inject and no default constructor" );
    }

    private Constructor getDefaultConstructor(Class clazz) {
        Constructor[] cnstrs = clazz.getConstructors();

        for ( Constructor cnstr : clazz.getConstructors() ) {
            if ( cnstr.getGenericParameterTypes().length == 0 ) {
                return cnstr;
            }
        }
        return null;
    }

    private Constructor getSingleConstructor(Class clazz) {
        Constructor[] cnstrs = clazz.getConstructors();

        if ( cnstrs.length > 1 ) {
            return null;
        }

        return cnstrs[0];
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

    private <T> Object[] findArguments( TypeLiteral<T> literal, Constructor<T> cnstr, Three three) {
        Object[]       pp    = new Object[cnstr.getParameterTypes().length];
        Annotation[][] anno  = cnstr.getParameterAnnotations();

        if ( cnstr.getParameterTypes().length == 1 && getAnnotatedName( anno[0]) == null ) {

            pp[0] = get( literal.getParameterTypes( cnstr ).get(0), three );
            return pp;
        }


        return findArguments( cnstr, three );
    }


    private <T> Object[] findArguments(Constructor<T> cnstr, Three three) {

        Type[]         paras = cnstr.getGenericParameterTypes();
        Object[]       pp    = new Object[cnstr.getParameterTypes().length];
        Annotation[][] anno  = cnstr.getParameterAnnotations();

        if ( !three.isMap() ) {
            throw new IllegalArgumentException( "three should be a map to read class " + cnstr + " but is: " + three );
        }
        Map<ThreeKey,Three> threeMap = three.getMap();

        for ( int i = 0; i < cnstr.getParameterTypes().length; ++i ) {

            String name = getAnnotatedName( anno[i]);
            if ( name == null ) {
                throw new Error("nop");
            }

            try {
                Key key = Key.get(paras[i]);
                Three elemRead = threeMap.get( new ThreeKey( name ));
                if ( elemRead == null ) {
                    throw new IllegalArgumentException("bad key " + name );
                }
                TypeLiteral literal  = key.getLiteral();
                pp[i] = get( literal, elemRead);
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



    private TypeLiteral getTypeParameter( TypeLiteral literal ) {

        if ( !(literal.getType() instanceof ParameterizedType )) {
            throw new IllegalArgumentException( "not a parameterized type, TypeLiteral<Foo> instead of TypeLiteral<List<Foo>> " + literal );
        }

        ParameterizedType pa = (ParameterizedType)literal.getType();

        if ( pa.getActualTypeArguments().length != 1 ) {
            throw new IllegalArgumentException("getTypeParemeter only works with single parameters not " + pa.getActualTypeArguments().length );
        }

        return com.google.inject.Key.get(pa.getActualTypeArguments()[0]).getLiteral();

    }

    private TypeLiteral getTypeParameter( TypeLiteral literal, int idx ) {

        if ( !(literal.getType() instanceof ParameterizedType )) {
            throw new IllegalArgumentException( "not a parameterized type, TypeLiteral<Foo> instead of TypeLiteral<List<Foo>> " + literal );
        }

        ParameterizedType pa = (ParameterizedType)literal.getType();

        if ( pa.getActualTypeArguments().length <= idx ) {
            throw new IllegalArgumentException("not enough type parameters for index " + idx + "in"+ literal );
        }

        return com.google.inject.Key.get(pa.getActualTypeArguments()[idx]).getLiteral();

    }

}
