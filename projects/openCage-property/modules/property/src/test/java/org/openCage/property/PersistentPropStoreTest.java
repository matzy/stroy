package org.openCage.property;

import org.junit.*;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.lang.functions.F1;
import org.openCage.io.With;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

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

public class PersistentPropStoreTest {

    private static File backing = FSPathBuilder.getTmpFile("xml").toFile();

    public static class SingletonPropertyProvider {

        public static Property<String> get() {
            return PersistentProp.get( new PersistingPropStore( new BackgroundExecutorImpl(), backing ), "key", "dflt", "description");
        }
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("down");
        backing.delete();
    }

    @BeforeClass
    public static void setUp() {
        System.out.println("up");
        backing.delete();

        new With().withWriter( backing, new F1<Void, Writer>() {
            @Override
            public Void call(Writer writer) {
                try {
                    writer.append("<map>\n" +
                            "  <entry>\n" +
                            "    <string>key</string>\n" +
                            "    <Property>\n" +
                            "      <obj class=\"string\">AAA</obj>\n" +
                            "      <description>description</description>\n" +
                            "    </Property>\n" +
                            "  </entry>\n" +
                            "</map>");
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return null;
            }
        });
    }

    @Test
    public void testA() {
        Property<String> a = SingletonPropertyProvider.get();

//        assertEquals( "dflt", a.get() );

        a.set( "AAA");
    }

//    @Test
//    public void testB() {
//
//        File ff = backing;
//
//        Property<String> b = SingletonPropertyProvider.the.get();
//        assertEquals( "AAA", b.get() );
//    }

}
