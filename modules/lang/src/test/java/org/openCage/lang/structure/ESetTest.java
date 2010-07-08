package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
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
