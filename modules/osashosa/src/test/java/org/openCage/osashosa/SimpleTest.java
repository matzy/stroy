//package org.openCage.sip;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import com.google.inject.name.Named;
//import org.junit.Test;
//
//import static junit.framework.Assert.assertEquals;
//
//public class SimpleTest {
//
//    public static interface Foo {}
//
//    public static class FooImpl implements Foo {
//        @Inject
//        public FooImpl() {}
//    }
//
//    @Test
//    public void testInter() {
//        Injector inj = new Injector();
//        inj.bind( Foo.class, FooImpl.class );
//
//
//        Foo foo = inj.getInstance( Foo.class );
//    }
//
//    public static interface One {}
//
//    public static class OneImpl implements One {
//
//        final private Foo foo;
//
//        @Inject
//        public OneImpl( Foo foo ) { this.foo = foo; }
//
//        @Override
//        public String toString() {
//            return "OneImpl{" +
//                    "foo=" + foo +
//                    '}';
//        }
//    }
//
//    @Test
//    public void testOne() {
//        Injector inj = new Injector();
//        inj.bind( Foo.class, FooImpl.class );
//        inj.bind( One.class, OneImpl.class );
//
//
//        One one = inj.getInstance( One.class );
//
//        System.out.println( one );
//    }
//
//
//    ////////////////////////////////////
//
//    public static interface Two {}
//
//    public static class StringPro implements Provider<String> {
//
//        @Inject public StringPro(){}
//
//        @Override
//        public String get() {
//            return "provided";
//        }
//    }
//
//
//    public static class TwoImpl implements Two {
//        @Inject public TwoImpl( One one, String str ) {
//            System.out.println("Two Impl with one and " + str );
//        }
//    }
//
//    @Test
//    public void testProvider() {
//        Injector inj = new Injector();
//        inj.bind( Foo.class, FooImpl.class );
//        inj.bind( One.class, OneImpl.class );
//        inj.bind( Two.class, TwoImpl.class );
//        inj.bindToProvider( String.class, StringPro.class );
//
//
//        Two two = inj.getInstance( Two.class );
//
//    }
//
//    ////////////////////////////////////
//
//    public static class OtherStringPro implements Provider<String> {
//
//        @Inject public OtherStringPro(){}
//
//        @Override
//        public String get() {
//            return "named";
//        }
//    }
//
//    public static class Naming {
//
//        final public String res;
//
//        @Inject public Naming( String str, @Named( "other") String o ) {
//            res = str +" and " + o;
//        }
//    }
//
//    @Test
//    public void testNamed() {
//        Injector inj = new Injector();
//        inj.bind( Foo.class, FooImpl.class );
//        inj.bind( One.class, OneImpl.class );
//        inj.bind( Naming.class, Naming.class );
//        inj.bindToProvider( String.class, StringPro.class );
//        inj.bindToProvider( "other", String.class, OtherStringPro.class );
//
//
//        Naming nn = inj.getInstance( Naming.class );
//
//        assertEquals( "provided and named", nn.res );
//
//    }
//
//}
