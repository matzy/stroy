package org.openCage.lang.inc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class ImmuDateTest {


    @Test
    public void testFrac() {
        ImmuDate im = ImmuDate.valueOf("2012-06");
        assertNotNull(im);
        im = ImmuDate.valueOf("2012-06-13");
        assertNotNull(im);
        im = ImmuDate.valueOf("2012-06-13 14");
        assertNotNull(im);
        im = ImmuDate.valueOf("2012-06-13 14:15");
        assertNotNull(im);
        im = ImmuDate.valueOf("2012-06-13 14:15:27");
        assertNotNull(im);
        im = ImmuDate.valueOf("2012-06-13 14:15:27-101");
        assertNotNull(im);

    }

    @Test
    public void testEq() {
        assertEquals( "2012-06-13 14:15:07-000", ImmuDate.valueOf("2012-06-13 14:15:07").toString());
    }


//    @Test
//    public void testFromReadable() {
//        ImmuDate id = new Buildin().getFromReadable( ImmuDate.class ).call( RString.valueOf( "Mon Jun 11 16:56:11 2012"));
//
//        System.out.println(id);
//        assertEquals( new ImmuDate( new Date(1339426571529L)), id );
//    }
}
