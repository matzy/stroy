package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BindWithTypeliteral {

    // guice requires an constructor with @inject
    // to use a alreasy existing class you have to wrap it
    public static class ArrayListWrapper<T> extends ArrayList<T> {

        @Inject public ArrayListWrapper() {
            super();
        }
    }

    public static class ListInteger {
        public List<Integer> ll;

        @Inject public ListInteger(List<Integer> ll) {
            this.ll = ll;
        }
    }

    public static class ListString {
        public List<String> ll;

        @Inject public ListString(List<String> ll) {
            this.ll = ll;
        }
    }

    @Test
    public void testList() {
        Injector inj = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(new TypeLiteral<List<Integer>>() {}).to(new TypeLiteral<ArrayListWrapper<Integer>>(){});
            }
        });

        ListInteger base = inj.getInstance(ListInteger.class);

        assertTrue( base.ll instanceof ArrayList );

    }

    @Test( expected = UnsupportedOperationException.class )
    public void testBadTypeParam() {
        Injector inj = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(new TypeLiteral<List<Integer>>() {}).to(new TypeLiteral<ArrayListWrapper<Integer>>(){});
            }
        });

        // show guice/osashosa strength
        // inject respects typeparam
        // List<String> ist not bound => exception
        ListString ot = inj.getInstance(ListString.class);
    }
}
