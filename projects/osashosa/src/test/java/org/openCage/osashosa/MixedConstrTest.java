package org.openCage.osashosa;

import com.google.inject.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/9/12
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class MixedConstrTest {

    public class Duh {
        @Inject
        public Duh() {}
    }

    public static class Foo {
        public Integer a;

        @Inject
        public Foo( @Direct Integer a, Duh duh ) {
            this.a = a;
        }

    }

    public static class Bar {
        public Foo foo;

        @Inject
        public Bar( Factory<Foo,Integer> foo ) {
            this.foo = foo.get(5);
        }
    }

    @Test
    public void testFactory() {
        Injector inj = Guice.createInjector(Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
            }
        });

        Bar b = inj.getInstance( Bar.class );

        assertNotNull(b);
        assertNotNull( b.foo );
        assertEquals( new Integer(5), b.foo.a );

    }

    public static class BarFail {
        public Foo foo;

        @Inject
        public BarFail( Factory<Foo,String> foo ) {
            this.foo = foo.get("5");
        }
    }

    @Test( expected = Unchecked.class )
    public void testFactoryFail() {
        Injector inj = Guice.createInjector(Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
            }
        });

        BarFail b = inj.getInstance( BarFail.class );

    }


}
