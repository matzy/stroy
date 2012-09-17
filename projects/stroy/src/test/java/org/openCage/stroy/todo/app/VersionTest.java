package org.openCage.stroy.todo.app;

import junit.framework.TestCase;

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

public class VersionTest extends TestCase {

    public void testParse() {
        assertEquals( new Version2(0,123,456), Version2.parseVersion( "0.123.456" ));
    }

    public void testParse2() {
        assertEquals( new Version2(0,123,456,789), Version2.parseVersion( "0.123.456.789" ));
    }

    public void testCompare() {
        assertTrue( new Version2( 0,10,200).compareTo( new Version2( 0,11,0,10)) < 0  );
        assertTrue( new Version2( 0,10,11).compareTo( new Version2( 0,10,200 )) < 0  );
        assertTrue( new Version2( 0,11,0,42).compareTo( new Version2( 0,11,1,10)) < 0  );
        assertTrue( new Version2( 0,10,20,4242).compareTo( new Version2( 1,0,1,1001)) < 0  );
        assertTrue( new Version2( 0,10,20,4242).compareTo( new Version2( 0,11,1,1001)) < 0  );

        // !! old and new version  number
        assertTrue( new Version2( 1,20,200).compareTo( new Version2( 0,0,0,1)) < 0  );

    }
}
