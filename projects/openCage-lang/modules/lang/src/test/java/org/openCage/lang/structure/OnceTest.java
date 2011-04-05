package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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


public class OnceTest {

    @Test
    public void testOnce() {
        Once<String> o = new Once<String>( "dflt");

        assertFalse( o.isSet() );

        o.set( "foo");
        assertTrue( o.isSet() );
        assertEquals( "foo", o.get() );

    }

    @Test( expected = IllegalStateException.class )
    public void setAfterGet() {
        Once<String> o = new Once<String>( "dflt");
        o.get();
        o.set("b");
    }

    @Test( expected = IllegalStateException.class )
    public void setTwiceBad() {
        Once<String> o = new Once<String>( "dflt");

        o.set("a");
        o.set("b");
    }

    @Test
    public void setTwiceGood() {
        Once<String> o = new Once<String>( "dflt");

        o.set("a");
        o.set("a");

        assertTrue( o.isSet());
    }

    @Test
    public void setIf() {
        Once<String> to = new Once<String>( "to");
        Once<String> from = new Once<String>( "from");

        to.setIf( from );
        assertFalse( to.isSet() );

        from.set( "yes" );
        to.setIf( from );
        assertTrue( to.isSet() );
        assertEquals( "yes", to.get() );
    }

    @Test
    public void setIfNothingTodo() {
        Once<String> to = new Once<String>( "to");
        Once<String> from = new Once<String>( "from");


        to.set("done");
        to.setIf( from );

        assertEquals( "done", to.get() );
    }

    @Test( expected = IllegalStateException.class ) 
    public void setIfFail() {
        Once<String> to = new Once<String>( "to");
        Once<String> from = new Once<String>( "from");


        to.set("done");
        from.set("also");

        to.setIf( from );
    }


}
