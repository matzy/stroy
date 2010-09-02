package org.openCage.lang.iterators;

import org.junit.Test;
import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.Count;
import org.openCage.lang.structure.T2;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

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

public class IteratorsTest {

    @Test
    public void testSimple() {
        int tested = 0;        
        for ( Count<Character> ch : Count.count( Iterators.chars( "01234"))) {
            assertEquals( new Integer( ch.idx()), Integer.valueOf( ""+ ch.obj() )  );
            tested++;
        }

        assertEquals(5, tested);
    }

    @Test
    public void testWords() {

        int tested = 0;
        for ( ArrayCount<String> ch : ArrayCount.count( Iterators.words( "0 1   2   3 4 5 6 7 8 9 10    11\t12"))) {
            assertEquals( new Integer( ch.idx()), Integer.valueOf( ""+ ch.obj() )  );
            tested++;
        }

        assertEquals(13, tested);
    }

    @Test
    public void testLines() {

        int tested = 0;

        for (T2<String,String> pair : Iterators.parallel(
                Iterators.lines( "111\n\r2 2\n\n\n\n\n   3\r4\t4"),
                Arrays.asList( "111", "2 2", "   3", "4\t4"))) {
            assertEquals( pair.i0, pair.i1 );
            tested++;
        }

        assertEquals( 4, tested);

    }

    @Test
    public void testParallel() {
        for ( T2<Integer,String> pair : Iterators.parallel( Arrays.asList( 1,2,3), Arrays.asList("1", "2"))) {
            assertEquals( pair.i0.toString(), pair.i1);
        }
    }
}
