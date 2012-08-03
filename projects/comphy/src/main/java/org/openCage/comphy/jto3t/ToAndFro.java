//package org.openCage.comphy.conv;
//
//import com.google.inject.*;
//import com.google.inject.name.Named;
//import org.openCage.comphy.*;
//import org.openCage.comphy.ThreeText;
//import org.openCage.comphy.property.ImmuProp;
//import org.openCage.comphy.property.MapProperty;
//import org.openCage.lang.inc.GMap;
//import org.openCage.lang.functions.F1;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static org.openCage.comphy.Readables.R;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// ***** END LICENSE BLOCK *****/
//public class ToAndFro implements Deread {
//
//    private Basics basics = Basics.get();
//
//    public ToAndFro() {
//    }
//
//    @Override
//    public <T> T get(TypeLiteral<? extends T> typeLiteral, org.openCage.comphy.ThreeText readable) {
//        if ( readable == null ) {
//            throw new IllegalArgumentException("bad key (?)");
//        }
//
//        Class clazz = typeLiteral.getRawType();
//
//        F1<T,ThreeText> basic = basics.getFromReadable( clazz );
//        if ( basic != null ) {
//            return basic.call(readable);
//        }
//
//        if ( Map.class.isAssignableFrom( clazz )) {
//            return getMap(typeLiteral, clazz, readable.getMap());
//        }
//
//        if ( List.class.isAssignableFrom( clazz )) {
//            return getList(typeLiteral, readable, clazz);
//        }
//
//        // abstract prop
//
//        if ( AbstractImmutableProperty.class.isAssignableFrom( clazz )) {
//            return getImmutableProperty(readable, clazz);
//
//        }
//
//        if ( ImmuProp.class.isAssignableFrom( clazz )) {
//            return getImmuProp(readable, typeLiteral);
//
//        }
//
//        //enum
//
//        if ( clazz.isEnum()) {
//            return getEnum( readable, clazz);
//        }
//
//        // complex
//
//        if ( !readable.isMap() ) {
//            throw new IllegalStateException( "readable should be a map not " + readable );
//        }
//
//        Constructor cnstr = getConstructor( clazz );
//        Object[] args = findArguments( cnstr, readable.getMap() );
//
//        try {
//            return (T)cnstr.newInstance(args);
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        throw new Error("huh");
//    }
//
//    private <T> T getEnum( ThreeText readable, Class clazz) {
//        if ( !readable.isString()) {
//            throw new IllegalArgumentException("can't build enum from " + readable);
//        }
//
//        String name = readable.getString().get();
//        for ( Object en : clazz.getEnumConstants()) {
//            if ( en.toString().equals(name)) {
//                return (T)en;
//            }
//
//        }
//        throw new IllegalArgumentException( "can't convert " +  name + " to " + clazz);
//    }
//
//    private <T> T getImmutableProperty(ThreeText readable, Class clazz) {
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
//
//        throw new Error("oops");
//    }
//
//    private <T> T getImmuProp(ThreeText readable, TypeLiteral literal ) {
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
//
//        throw new Error("oops");
//    }
//
//
//    private <T> T getList(TypeLiteral<T> typeLiteral, ThreeText readable, Class clazz) {
//        Constructor cnstr = getConstructor( clazz );
//
//        if ( cnstr == null ) {
//            throw new IllegalStateException("no constructor for for tlist derevied class " + clazz );
//        }
//
//        try {
//            List list = (List)cnstr.newInstance();
//            TypeLiteral param = getTypeParameter(typeLiteral);
//            if ( readable.isList() ) {
//                for ( ThreeText elem : readable.getList() ) {
//                    list.add(get(param, elem));
//                }
//            } else if ( readable.isMap()) {
//                if ( readable.getMap().size() == 1 ) {
//                    // ok
//                    list.add( get( param, readable.getMap().values().iterator().next() ));
//                } else if ( readable.getMap().size() > 1 ) {
//                    throw new IllegalArgumentException( "list can not be build from maps with more than 1 element" );
//                }
//            }
//            // this accepts maps and strings as empty lists (maps is ok)
//            return (T)list;
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        throw new Error("foo");
//    }
//
//    private <T> T getMap(TypeLiteral<T> typeLiteral, Class clazz, GMap<Str,ThreeText> readable) {
//
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
//
//        throw new Error("huh");
//    }
//
//    @Override
//    public <T> T get(Class<T> clazz, ThreeText readable) {
//
//        return get( com.google.inject.Key.get(clazz).getLiteral(),readable);
//    }
//
//
//    private Constructor getConstructor( Class clazz ) {
//
//        if ( clazz.isInterface() ) {
//            throw new IllegalArgumentException( "can't inject an interface " + clazz );
//        }
//
//        Constructor ret = getInjectedConstructor(clazz);
//
//        if ( ret != null ) {
//            return ret;
//        }
//
//        // now get the default constructor
//        Constructor[] cnstrs = clazz.getConstructors();
//
//        for ( Constructor cnstr : clazz.getConstructors() ) {
//            if ( cnstr.getGenericParameterTypes().length == 0 ) {
//                return cnstr;
//            }
//        }
//
//        throw new ConfigurationException( "class " + clazz.getName() + " has no constructor annotated with @Inject and no default constructor" );
//    }
//
//
//    private Constructor getInjectedConstructor(Class clazz) {
//        Constructor[] cnstrs = clazz.getConstructors();
//
//        Constructor ret = null;
//
//        for ( Constructor cc : cnstrs ) {
//            if ( cc.isAnnotationPresent( Inject.class )) {
//                if ( ret == null ) {
//                    ret = cc;
//                } else {
//                    throw new ConfigurationException( "class " + clazz.getName() + " as more than one constructor annotated with @Inject");
//                }
//            }
//        }
//        return ret;
//    }
//
//    private Object[] findArguments(Constructor cnstr, GMap<Str,ThreeText> readable) {
//
//
//        Type[] paras = cnstr.getGenericParameterTypes();  //getParameterTypes();
//        Object[] pp = new Object[cnstr.getParameterTypes().length];
//        Annotation[][] anno = cnstr.getParameterAnnotations();
//
//        for ( int i = 0; i < cnstr.getParameterTypes().length; ++i ) {
//
//            String name = getAnnotatedName( anno[i]);
//            if ( name == null ) {
//                throw new Error("nop");
//            }
//
//            try {
//                Key key = Key.get(paras[i]);
//                ThreeText elemRead = readable.getG( S(name) );
//                if ( elemRead == null ) {
//                    throw new IllegalArgumentException("bad key " + name );
//                }
//                TypeLiteral literal  = key.getLiteral();
//                pp[i] = get( literal, elemRead);
//            } catch ( UnsupportedOperationException ex ) {
//                throw new UnsupportedOperationException( "Constructor " + cnstr.getName() + " can't be called because " + ex.getMessage() );
//            }
//        }
//
//        return pp;
//    }
//
//    String getAnnotatedName(Annotation[] annotations) {
//        for ( Annotation ann : annotations) {
//            if ( ann.annotationType().equals( Named.class )) {
//                return ((Named)ann).value();
//            }
//        }
//
//        return null;
//    }
//
//    public <T> ThreeText toReadable( T obj ) {
//
//        if ( obj instanceof Readalizable ) {
//            return ((Readalizable)obj).toReadable();
//        }
//
//        Class clazz = obj.getClass();
//
//        if ( clazz.isEnum() ) {
//            return R(obj.toString());
//        }
//
//        if (List.class.isAssignableFrom( clazz )) {
//            List<ThreeText> rl = new ArrayList<ThreeText>();
//            for ( Object elem : (List)obj ) {
//                rl.add( toReadable(elem));
//            }
//
//            return R(rl);
//        }
//
//        if ( SimpleMap.class.isAssignableFrom( clazz )) {
//
//        }
//
//        if (Map.class.isAssignableFrom(clazz)) {
////            for (Map.Entry entry : ((Map)obj).entrySet() ) {
////
////            }
//        }
//
//        F1<ThreeText, T> fct = (F1<ThreeText, T>) basics.getToReadable( clazz);
//
//        if ( fct == null ) {
//            throw new IllegalArgumentException( "not known to be readable " + clazz );
//        }
//
//        return fct.call(obj);
//    }
//
//
//    private TypeLiteral getTypeParameter( TypeLiteral literal ) {
//
//        if ( !(literal.getType() instanceof ParameterizedType )) {
//            throw new IllegalArgumentException( "not a parameterized type, TypeLiteral<Foo> instead of TypeLiteral<List<Foo>> " + literal );
//        }
//
//        ParameterizedType pa = (ParameterizedType)literal.getType();
//
//        if ( pa.getActualTypeArguments().length != 1 ) {
//            throw new IllegalArgumentException("getTypeParemeter only works with single parameters not " + pa.getActualTypeArguments().length );
//        }
//
//        return com.google.inject.Key.get(pa.getActualTypeArguments()[0]).getLiteral();
//
//    }
//
//
//}
