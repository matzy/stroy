package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BIMapTest {


    @Test
    public void testBasicMap() {
        BiMap<String,Integer> bim = new BiMap<String, Integer>();

        assertEquals(0, bim.size());
        assertNull( bim.get("A"));
        assertFalse( bim.containsKey( "A" ));
        assertFalse( bim.containsValue( 1 ));
        assertTrue( bim.isEmpty());


        bim.put( "A", 1 );

        assertEquals(1, bim.size());
        assertFalse( bim.isEmpty());
        assertTrue( bim.containsKey( "A" ));
        assertTrue( bim.containsValue( 1 ));


        bim.remove("A");
        assertEquals(0, bim.size());
        assertNull( bim.get("A"));
        assertFalse( bim.containsKey( "A" ));
        assertFalse( bim.containsValue( 1 ));
        assertTrue( bim.isEmpty());


        bim.put( "B", 2 );
        bim.put( "C", 3 );
        bim.clear();
        assertEquals(0, bim.size());
        assertNull( bim.get("A"));
        assertFalse( bim.containsKey( "A" ));
        assertFalse( bim.containsValue( 1 ));
        assertTrue( bim.isEmpty());

    }

    @Test
    public void testBi() {
        BiMap<String,Integer> bim = new BiMap<String, Integer>();
        bim.put( "A", 1 );

        assertEquals( new Integer(1), bim.get("A"));
        assertEquals( "A", bim.getReverse(1));
    }




}
