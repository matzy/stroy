package org.openCage.babel;

import org.junit.Test;
import org.openCage.lang.functions.F1;

import java.util.Arrays;
import java.util.Locale;
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
