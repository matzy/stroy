package org.openCage.lang.structure;

import org.junit.Test;
import org.openCage.lang.structure.MRU;

import static junit.framework.Assert.assertEquals;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class MRUTest {

    @Test
    public void testAdding() {
        MRU<String> mru = new MRU<String>();

        mru.use( "foo" );
        assertEquals( "foo", mru.getAll().iterator().next() );

        mru.use( "duda" );
        assertEquals( "duda", mru.getAll().iterator().next() );

        mru.use( "foo" );
        assertEquals( "foo", mru.getAll().iterator().next() );
        assertEquals( 2, mru.getAll().size());

        mru.use( "333" );
        assertEquals( "333", mru.getAll().iterator().next() );
        assertEquals( 3, mru.getAll().size());
        mru.setMaxSize( 2 );
        assertEquals( 2, mru.getAll().size());

        mru.use( "444" );
        assertEquals( "444", mru.getAll().iterator().next() );
        assertEquals( 2, mru.getAll().size());

        mru.clear();
        assertEquals( 0, mru.getAll().size() );
    }


    @Test( expected = IllegalArgumentException.class )
    public void setMaxSize() {
        new MRU<String>().setMaxSize(0);
    }
}
