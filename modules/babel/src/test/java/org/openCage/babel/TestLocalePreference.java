package org.openCage.babel;

import org.junit.Test;
import org.openCage.lang.functions.F1;

import java.util.Arrays;
import java.util.Locale;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TestLocalePreference {

    @Test
    public void testDefault() {
        assertEquals( Arrays.asList( Locale.getDefault(), Locale.ENGLISH), new LocalePreference().getLocales());
    }

    private static class Counter {
        private int count = 0;
        public void up() {
            count++;
        }

        public int get() {
            return count;
        }
    }

    @Test
    public void testChange() {
        LocalePreference lp = new LocalePreference();

        final Counter counter = new Counter();
        final List<Locale> next = Arrays.asList( Locale.TAIWAN, Locale.GERMAN );

        lp.getListenerControl().add( new F1<Void, List<Locale>>() {
            @Override
            public Void call(List<Locale> locales) {
                counter.up();
                assertEquals(next, locales );
                return null;
            }
        });

        lp.setLocales( next );

        assertEquals(1,counter.get());
    }
}
