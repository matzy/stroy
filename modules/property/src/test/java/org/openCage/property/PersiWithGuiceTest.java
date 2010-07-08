package org.openCage.property;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import org.junit.Test;
import org.openCage.lang.BackgroundExecutorImpl;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class PersiWithGuiceTest {

    public static class A {
        private Property<String> foo;

        @Inject
        public A( Property<String> prop ) {
            foo = prop;
        }

        public String getFoo() {
            return foo.get();
        }
    }

    public static class PropertyProvider implements Provider<Property<String>> {

        private PropStore store;

        @Inject public PropertyProvider( PropStore store ) {
            this.store = store;
        }

        public Property<String> get() {
//            return PersistentProp.get( new PersistingPropStore( new BackgroundExecutorImpl(), new File("/User/stephan/tmp/tt.back" )), "key", "dflt", "description");
            return PersistentProp.get( store, "key", "dflt", "descr");
        }
    }

    public static class PropStoreProvider implements Provider<PropStore> {

        @Inject public PropStoreProvider(){}

        @Override
        public PropStore get() {
            return new PersistingPropStore( new BackgroundExecutorImpl(), new File("/User/stephan/tmp/tt.back" ));
        }
    }



    private static class ATestModule implements Module {
        @Override
        public void configure(Binder binder) {
            // binder.bind( A.class ).to( A.class ); error in Guice
            binder.bind( new TypeLiteral<Property<String>>(){}).toProvider( PropertyProvider.class );
            binder.bind( PropStore.class ).toProvider( PropStoreProvider.class ).in( Singleton.class );
        }
    }

    @Test
    public void testProp() {
        Injector inj = Guice.createInjector( new ATestModule());

        A a = inj.getInstance( A.class );

        assertEquals( "dflt", a.getFoo() );
    }

}
