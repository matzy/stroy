package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

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

public class WithModuleTest {


    public static interface Foo {}

    public static class FooImpl implements Foo {
        @Inject
        public FooImpl() {}
    }

    public static interface One {}

    public static class OneImpl implements One {

        final private Foo foo;

        @Inject
        public OneImpl( Foo foo ) { this.foo = foo; }

        @Override
        public String toString() {
            return "OneImpl{" +
                    "foo=" + foo +
                    '}';
        }
    }

    public static interface Two {}

    public static class StringPro implements Provider<String> {

        @Inject public StringPro(){}

        @Override
        public String get() {
            return "provided";
        }
    }


    public static class TwoImpl implements Two {
        @Inject public TwoImpl( One one, String str ) {
            System.out.println("Two Impl with one and " + str );
        }
    }

    public static class OtherStringPro implements Provider<String> {

        @Inject public OtherStringPro(){}

        @Override
        public String get() {
            return "named";
        }
    }

    public static class Naming {

        final public String res;

        @Inject public Naming( String str, @Named( "other") String o ) {
            res = str +" and " + o;
        }
    }

    public static class Strings extends ArrayList<String > {
        @Inject public Strings() {}
    }

    public static class TL {

        private List<String> lst;

        @Inject public TL( List<String> strs ) {
            lst = strs;
            lst.add( "Hallo ");
            lst.add( "World");

        }

        public String toString() {
            return lst.get(0) + lst.get(1);
        }
    }

    public static class Sing {
        public static int num = 0;
        @Inject public Sing() {
            num++;
        }
    }

    public static class Multi {
        public static int num = 0;
        @Inject public Multi() {
            num++;
        }
    }

    public static class TestModule implements Module {

        @Override
        public void configure(Binder binder) {
            binder.bind( Foo.class ).to( FooImpl.class );
            binder.bind( One.class ).to( OneImpl.class );
            binder.bind( Two.class ).to( TwoImpl.class );
            binder.bind( String.class ).toProvider( StringPro.class );
            binder.bind( Naming.class ).to( Naming.class );
            binder.bind( String.class ).annotatedWith( Names.named("other")).toProvider( OtherStringPro.class );

            binder.bind( new TypeLiteral<List<String>>() {} ).
                    to( Strings.class );
                    //in( Singleton.class );

            binder.bind( TL.class ).to( TL.class );

            binder.bind( Sing.class ).to( Sing.class ).in( Singleton.class );
            binder.bind( Multi.class ).to( Multi.class );
        }
    }


    @Test
    public void testInstance() {
        Injector inj = Guice.createInjector(new TestModule());

        Foo foo = inj.getInstance( Foo.class );
    }

    @Test
    public void testOne() {
        Injector inj = Guice.createInjector(new TestModule());

        One one = inj.getInstance( One.class );

        System.out.println( one );
    }

    @Test
    public void testProvider() {
        Injector inj = Guice.createInjector(new TestModule());

        Two two = inj.getInstance( Two.class );

    }

    @Test
    public void testNamed() {
        Injector inj = Guice.createInjector(new TestModule());

        Naming nn = inj.getInstance( Naming.class );

        assertEquals( "provided and named", nn.res );
    }

    @Test
    public void testLit() {
        Injector inj = Guice.createInjector(new TestModule());

        TL tl = inj.getInstance( TL.class );

        assertEquals( "Hallo World", tl.toString() );
    }

    @Test
    public void testSing() {
        Injector inj = Guice.createInjector(new TestModule());

        Multi m1 = inj.getInstance( Multi.class );
        Multi m2 = inj.getInstance( Multi.class );

        assertEquals( 2, Multi.num );

        Sing sing = inj.getInstance( Sing.class );
        Sing sing2 = inj.getInstance( Sing.class );

        assertEquals( 1, Sing.num );
    }


    public static class Prov {
        public String get() {
            return "foo";
        }
    }

//    @Test
//    public void testClassConv() {
//        Class<Prov> pp = Prov.class;
//
//        Class<? extends Provider<? extends String>> s = (Class<? extends Provider<? extends String>>) pp;
//
//        try {
//            Provider<String> p = (Provider<String>) s.newInstance();
//            String str = p.get();
//            System.out.println( p.get());
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }

}
