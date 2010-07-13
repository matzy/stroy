package org.openCage.artig;

import com.google.inject.Binder;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provider;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class ConstructorTest {

    public static class Dflt {
        public Dflt() {}
    }

//    public static class DfltModule implements Module {
//
//        @Override
//        public void configure(Binder binder) {
//        }
//    }

    @Test
    public void defaultCnstr() {
        Guice.createInjector().getInstance( Dflt.class );
    }

    public static class One {
        @Inject
        public One() {
            System.out.println("one");}

        public One( int i ) {}
    }

    @Test
    public void oneInject() {
        Guice.createInjector().getInstance( One.class );
    }


    public static class Arguments {
        public Arguments( One i ) {}
    }

    @Test( expected = ConfigurationException.class )
    public void arguments() {
        Guice.createInjector().getInstance( Arguments.class );
    }

    public static class MultipleInjects {
        @Inject
        public String foo;

        @Inject public MultipleInjects() {}
    }

    @Test //( expected = ConfigurationException.class )
    public void multipleInjects() {
        Guice.createInjector().getInstance( MultipleInjects.class );
    }

    public static class MultipleInjects2 {
        @Inject
        public One foo;

        @Inject public MultipleInjects2( One two) {}
    }

    @Test //( expected = ConfigurationException.class )
    public void multipleInjects2() {
        Guice.createInjector().getInstance( MultipleInjects2.class );
    }

    public static class InjectedContrs {
        @Inject public InjectedContrs() {}
        @Inject public InjectedContrs( One one ) {}
    }

    @Test( expected = ConfigurationException.class )
    public void injectedContrs() {
        Guice.createInjector().getInstance( InjectedContrs.class );
    }


    public static class MethodInject {
        public One foo;

        public MethodInject() {}

        @Inject public void setFoo( One one ) { foo = one;};
    }

    @Test //( expected = ConfigurationException.class )
    public void methodInject() {
        MethodInject mi = Guice.createInjector().getInstance( MethodInject.class );
        assertNotNull( mi.foo );
    }

//    public static interface IFace {}
//
//    public static class PIF implements Provider<IFace> {
//
//        @Override
//        public IFace get() {
//            return new IFace(){};
//        }
//    }
//
//    public static class Mod implements Module {
//
//        @Override
//        public void configure(Binder binder) {
//            binder.bind( IFace.class ).to( PIF.class );
//        }
//    }


    public static class BaseMod implements Module {

        public static int count = 0;

        @Override public void configure(Binder binder) { count++; }

        @Override
        public boolean equals(Object o) {
            return o != null && (o instanceof BaseMod);
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }
    }

    public static class AMod implements Module {

        @Override
        public void configure(Binder binder) {
            binder.install( new BaseMod() );
        }
    }

    public static class BMod implements Module {

        @Override
        public void configure(Binder binder) {
            binder.install( new BaseMod() );
        }
    }

    @Test
    public void testSharedModule() {
        Guice.createInjector( new AMod(), new BMod() );

        assertEquals( 1, BaseMod.count );
    }


}
