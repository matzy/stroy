package org.openCage.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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


public class FriendlySingltonAppTest {

    private static File getTmpFile() {
        String dir = System.getProperty("java.io.tmpdir");
        if ( !dir.endsWith( File.pathSeparator )) {
            dir += File.pathSeparator;
        }

        dir += "FriendlySingltonAppTest-tmp1.tmp";

        return new File( dir );
    }

    @Before
    public void setup() {
        File tmp = getTmpFile();

        if ( tmp.exists()) {
            tmp.delete();
        }
    }

    @After
    public void tearDown() {
        File tmp = getTmpFile();

        if ( tmp.exists()) {
            tmp.delete();
        }        
    }

    @Test
    public void testCreateFile() {
        File tmpFile = getTmpFile();
        
        assertNotNull( tmpFile.getParentFile());
        assertFalse( tmpFile.exists());

        SingletonApp single = new FriendlySingletonApp( tmpFile );

        assertTrue( tmpFile.exists());
        assertTrue( single.isMaster() );

        SingletonApp single2 = new FriendlySingletonApp( tmpFile );
        assertTrue( single.isMaster() );
        assertFalse( single2.isMaster() );
    }

}
