package org.openCage.comphy;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import org.junit.Test;
import org.openCage.lang.inc.GHashMap;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Str;
import org.openCage.lang.inc.Strng;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.openCage.comphy.Readables.R;
import static org.openCage.lang.inc.Strng.S;

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

public class FromReadableTest {


    @Test
    public void testReadString() {
        ToAndFro ps = new ToAndFro();

        String foo = ps.get( String.class, R("foo") );

        assertEquals("foo", foo);
    }

    @Test
    public void testStringProperty() {
        ToAndFro ps = new ToAndFro();

        StringProperty foo = ps.get( StringProperty.class, R("foo") );

        assertEquals( new StringProperty("foo"), foo);
    }

    @Test
    public void testStrProperty() {
        ToAndFro ps = new ToAndFro();

        ImmuProp<Str> foo = (ImmuProp) ps.get( new TypeLiteral<ImmuProp<Strng>>() {}, R("foo") );

        assertEquals( new ImmuProp<Str>(S("foo")), foo);
    }

    @Test
    public void testReadLocale() {
        ToAndFro ps = new ToAndFro();

        Locale loc = ps.get( Locale.class, R(Locale.US.toLanguageTag()));

        assertEquals("en-US", loc.toLanguageTag());
    }

    @Test
    public void tesCompo1() {

        ToAndFro ps = new ToAndFro();

        GMap<Str,Readable> map = new GHashMap<Str, Readable>();
        map.put(S("str"), R("string"));
        map.put(S("loc"), R(Locale.US.toLanguageTag()));

        Compo1 compo = ps.get( Compo1.class, R(map) );

        assertEquals(new Compo1("string", Locale.US), compo);
    }

    public static class Compo1 {
        private final String str;
        private final Locale loc;

        @Inject
        public Compo1( @Named( value = "str") String str, @Named( value = "loc") Locale loc) {
            this.str = str;
            this.loc = loc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Compo1 compo1 = (Compo1) o;

            if (loc != null ? !loc.equals(compo1.loc) : compo1.loc != null) return false;
            if (str != null ? !str.equals(compo1.str) : compo1.str != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = str != null ? str.hashCode() : 0;
            result = 31 * result + (loc != null ? loc.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Compo1{" +
                    "str='" + str + '\'' +
                    ", loc=" + loc +
                    '}';
        }
    }

    @Test
    public void testList() {
        ToAndFro ps = new ToAndFro();

        List<Readable> rl = new ArrayList<Readable>();
        rl.add( R("one"));
        rl.add( R("two"));

        List<String> res = ps.get( new TypeLiteral<ArrayList<String>>() {}, R(rl)  );

        assertEquals("two", res.get(1));
    }



    @Test
    public void testList2() {
        ToAndFro ps = new ToAndFro();


        List<Readable> rl = new ArrayList<Readable>();
        rl.add( R(Locale.US.toLanguageTag()));
        rl.add( R(Locale.GERMAN.toLanguageTag()));
        GMap<Str,Readable> ls = new GHashMap<Str, Readable>();
        ls.put( S("locales"), R(rl));
        ls.put( S("selection"), R( Locale.US.toLanguageTag()));

        ListSelection<Locale> res = ps.get( LocaleSelection.class, R(ls)  );

        assertEquals( Locale.GERMAN, res.list.get(1));

    }

    public static class ListSelection<T> {
        private final List<T> list;
        private final T       selection;


        public ListSelection( List<T> list, T selection) {
            this.list = list;
            this.selection = selection;
        }
    }

    public static class LocaleSelection extends ListSelection<Locale> {

        @Inject
        public LocaleSelection( @Named(value = "locales") ArrayList<Locale> locales, @Named(value = "selection") Locale selection) {
            super(locales, selection);
        }
    }
}
