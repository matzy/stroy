package org.openCage.lang.iterators;

import org.junit.Test;
import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.Count;
import org.openCage.lang.structure.T2;

import java.util.Arrays;

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
