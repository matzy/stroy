package org.openCage.babelgen;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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

public class BabelGenTest {

    @Test
    public void testSimple() {
        new BabelGen( "org.openCage.babelgen.testbundle").generate( "org.openCage.testing", "TestBundle" );
    }

    @Test
    public void testFromFile() throws Exception {


        InputStream is = this.getClass().getResourceAsStream( "testbundle_en.properties");

        assertNotNull( is );

        String res = new BabelGen( "org.openCage.babelgen.testbundle",
                new PropertyResourceBundle( is )). //new FileInputStream( "/Users/stephan/projects/opencage-libs/modules/babelgen/src/test/resources/org/openCage/babelgen/testbundle_en.properties" ))).
                generate( "org.openCage.testing", "TestBundle" );

        // class name
        assertTrue( -1 < res.indexOf( "public class TestBundle" ) );

        // getter without args
        assertTrue( -1 < res.indexOf( "public String aaa(  ){" ) );

        // getter with args
        assertTrue( -1 < res.indexOf( "String ccc( String arg0, String arg1 ){" ) );
    }

    @Test
    public void testMakeName() {
        assertEquals( "org_oo", BabelGen.makeName("org.oo"));
    }


}
