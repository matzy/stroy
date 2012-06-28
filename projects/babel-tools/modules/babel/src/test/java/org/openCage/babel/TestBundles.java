package org.openCage.babel;

import org.junit.Ignore;
import org.junit.Test;
import sun.util.CoreResourceBundleControl;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

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


@Ignore
// testing bundles and what not works
public class TestBundles {

    @Test
    public void tt() {
        ResourceBundle fallback = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("") );

        assertEquals("ffff", fallback.getString("fallbackonly"));

        int i = 0;

        ResourceBundle de = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("de") );

        //assertEquals("ffff", de.getString("fallbackonly"));

        Enumeration<String> keys = de.getKeys();
        while ( keys.hasMoreElements() )  {
            System.out.println(keys.nextElement());
        }

        for ( String key : de.keySet() ){
            System.out.println(key);
        }

        //de.



        assertFalse(de.containsKey("fallbackonly"));


    }

    @Test
    public void testControl() {
        ResourceBundle de = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("de"),
                new ResourceBundle.Control() {
                    @Override
                    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
                        if (baseName == null)
                            throw new NullPointerException();
                        if (locale.equals(new Locale("de"))) {
                            return Arrays.asList(
                                    locale,
                                    new Locale("es"),
//                                    Locale.TAIWAN,
                                    // no Locale.CHINESE here
                                    Locale.ROOT);
                        } else if (locale.equals(Locale.TAIWAN)) {
                            return Arrays.asList(
                                    locale,
                                    // no Locale.CHINESE here
                                    Locale.ROOT);
                        }
                        return super.getCandidateLocales(baseName, locale);
                    }
                } );

        for ( String key : de.keySet() ){
            System.out.println(key);
        }
    }

    public static class Ctrl extends  ResourceBundle.Control {
        private boolean first = true;

        @Override
        public List<Locale> getCandidateLocales(String baseName, Locale locale) {
            if ( first ) {
                return Arrays.asList( Locale.ENGLISH, Locale.ROOT );
            }

            return Arrays.asList( Locale.GERMAN, Locale.ROOT );
        }

        public void second() {
            first = false;
        }
    }

    @Test
    public void testChangeControl() {
        Ctrl ctrl = new Ctrl();

        ResourceBundle rs = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("de"), ctrl );

        assertEquals( "eee", rs.getString( "everywhere"));

        ctrl.second();
        assertEquals( "sss", rs.getString( "everywhere")); // nop
    }
}
