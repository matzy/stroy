package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
