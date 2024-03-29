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
