package org.openCage.osashosa;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoopDetection {

    public static class A {
        @Inject public A( B b) {}
    }
    public static class B {
        @Inject public B( C c ) {}

    }
    public static class C {
        @Inject public C( A a ) {}
    }

    @Test( expected = ConfigurationException.class )
    public void test3Loop() {
        Injector inj = Guice.createInjector( Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
            }
        });

        A a = inj.getInstance( A.class);

        assertTrue( a instanceof Object);

    }
    
    public static interface I{}
    
    public static class One implements I {
        @Inject public One( @Named("Foo") I i ) {}
    }
    
    public static class Two implements I {}

    @Test
    public void testNoLoop() {
        Injector inj = Guice.createInjector( Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind( I.class ).to( One.class );
                binder.bind( I.class ).annotatedWith( Names.named("Foo")).to( Two.class );

            }
        });

        I a = inj.getInstance( I.class);

        assertTrue( a instanceof One );

    }

}
