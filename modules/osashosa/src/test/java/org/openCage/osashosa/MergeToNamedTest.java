package org.openCage.sip;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * demonstrates howto bind one class to to different named injects
 */
public class MergeToNamedTest {

    public static class A {
        @Inject
        public A( @Named("A") String str ) {
            System.out.println( str );
        }
    }

    public static class B {
        @Inject
        public B( @Named("B") String str ) {
            System.out.println( str );
        }
    }

    public static class Merge implements Provider<String>  {

        public String merged;

        @Inject
        public Merge( String str ) { merged = str; }

        @Override
        public String get() {
            return "foo";
        }
    }

    public static class Sing implements Provider<String> {

        public static int count = 0;

        @Inject public Sing() {
            System.out.println("singggggggg");
            count++;
        }

        @Override
        public String get() {
            return "foo";
        }
    }


    @Test
    public void test() {

        Injector inj = Guice.createInjector( new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind( A.class ).to( A.class );
                binder.bind( B.class ).to( B.class );
                binder.bind( String.class ).annotatedWith( Names.named( "A" )).toProvider( Merge.class);
                binder.bind( String.class ).annotatedWith( Names.named( "B" )).toProvider( Merge.class);
                binder.bind( String.class ).toProvider( Sing.class ).in(Singleton.class );
            }
        });

        inj.getInstance( A.class );
        inj.getInstance( B.class );

        assertEquals( 1, Sing.count );

    }


}
