package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
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

public class ESetTest {

    public static class Key {
        public int i;
        public String payload;

        public Key( int i, String pl ) {
            this.i = i;
            this.payload = pl;
        }
        

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;

            Key key = (Key) o;

            if (i != key.i) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }


    @Test
    public void testAdd() {

        ESet<Key> set = new ESet<Key>();

        set.add( new Key(1,"A"));

        assertEquals( "A", set.get( new Key(1,"B")).payload);

        assertEquals( new Key(1,"C"), set.get( new Key(1,"B")));
        assertEquals( new Key(1,"E"), set.getAdd( new Key(1,"D")));

        assertNull( set.get( new Key(2,"F")));
        assertEquals( new Key(2,"G"), set.getAdd( new Key(2,"F")));

        assertTrue( set.add( new Key(1,"X")));

        assertFalse( set.remove( new Key(3,"3")));
        assertTrue( set.remove( new Key(1,"3")));
        assertFalse( set.contains( new Key(1,"7")));

        assertEquals( 1, set.size() );
        set.clear();
        assertEquals( 0, set.size() );
        assertTrue( set.isEmpty());
    }

    @Test
    public void setStuff() {
        ESet<String> set = new ESet<String>();

        assertFalse( set.contains("foo"));
        assertEquals( 0, set.size() );
        assertTrue( set.isEmpty());

        for ( String str : set ) {
        }
    }




}
