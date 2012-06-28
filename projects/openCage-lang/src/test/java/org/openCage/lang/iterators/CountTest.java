package org.openCage.lang.iterators;

import org.junit.Test;
import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.ByteCount;
import org.openCage.lang.iterators.Count;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

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


public class CountTest {

    @Test
    public void testCount() {
        List<Integer> list = Arrays.asList( 0, 1, 2, 3, 4 );

        int i = 0;
        for ( Count<Integer> ci : Count.count( list )) {
            assertEquals( "index: ", i, ci.idx() );
            assertEquals( "elem: ", i, ci.obj().intValue() );
            assertEquals( "first: ", i == 0 , ci.isFirst() );
            assertEquals( "last: ", i == 4 , ci.isLast() );

            i++;
        }
    }

    @Test
    public void testArrayCount() {
        Integer[] ii = {0,1,2,3,4};

        int i = 0;
        for ( ArrayCount<Integer> ci : ArrayCount.count(ii)) {
            assertEquals( "index: ", i, ci.idx() );
            assertEquals( "elem: ", i, ci.obj().intValue() );
            assertEquals( "first: ", i == 0 , ci.isFirst() );
            assertEquals( "last: ", i == 4 , ci.isLast() );

            i++;

        }
    }

    @Test
    public void testByteCount() {
        byte[] ii = {0,1,2,3,4};

        int i = 0;
        for ( ByteCount ci : ByteCount.count(ii)) {
            assertEquals( "index: ", i, ci.idx() );
            assertEquals( "elem: ", i, ci.obj().intValue() );
            assertEquals( "first: ", i == 0 , ci.isFirst() );
            assertEquals( "last: ", i == 4 , ci.isLast() );

            i++;

        }
    }

}
