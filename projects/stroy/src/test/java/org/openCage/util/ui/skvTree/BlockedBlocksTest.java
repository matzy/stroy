package org.openCage.util.ui.skvTree;

import junit.framework.TestCase;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

public class BlockedBlocksTest extends TestCase {

    public void testSingleSimple() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111"));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 10 );

        assertEquals( "111", bb.getByPoint( 5 ).get(0) );
    }

    public void testSingleNotFit() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111"));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 12 );

        assertEquals( "111", bb.getByPoint( 11 ).get(0) );
    }

    public void testSingleTooMuchSpace() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111"));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 189 );

        assertEquals( "111", bb.getByPoint( 101 ).get(0) );
    }

    public void testFewSimple() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111" ));
        src.add( Arrays.asList( "222" ));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 10 );

        assertEquals( "111", bb.getByPoint( 5 ).get(0) );
        assertEquals( "222", bb.getByPoint( 5 ).get(1) );
    }

    public void testFewNotFit() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111" ));
        src.add( Arrays.asList( "222" ));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 12 );

        assertEquals( "111", bb.getByPoint( 11 ).get(0) );
        assertEquals( "222", bb.getByPoint( 11 ).get(1) );
    }

    public void testFewTooMuchSpace() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111" ));
        src.add( Arrays.asList( "222" ));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 189 );

        assertEquals( "222", bb.getByPoint( 101 ).get(0) );
        assertEquals( "222", bb.getByPoint( 171 ).get(0) );
    }

    public void testMoreSimple() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111" ));
        src.add( Arrays.asList( "222" ));
        src.add( Arrays.asList( "333" ));
        src.add( Arrays.asList( "444" ));
        src.add( Arrays.asList( "555" ));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 10, 10 );

        assertEquals( "111", bb.getByPoint( 5 ).get(0) );
        assertEquals( "222", bb.getByPoint( 5 ).get(1) );
        assertEquals( "333", bb.getByPoint( 5 ).get(2) );
        assertEquals( "444", bb.getByPoint( 5 ).get(3) );
        assertEquals( "555", bb.getByPoint( 5 ).get(4) );
    }

    public void testMore3() {
        List<List<String>> src = new ArrayList<List<String>>();
        src.add( Arrays.asList( "111" ));
        src.add( Arrays.asList( "222" ));
        src.add( Arrays.asList( "333" ));
        src.add( Arrays.asList( "444" ));
        src.add( Arrays.asList( "555" ));

        BlockedBlocks<String> bb = new BlockedBlocks<String>( src, 5, 17 );

        assertEquals( "111", bb.getByPoint( 2 ).get(0) );
        assertEquals( "222", bb.getByPoint( 2 ).get(1) );
        assertEquals( "333", bb.getByPoint( 6 ).get(0) );
        assertEquals( "444", bb.getByPoint( 12 ).get(0) );
        assertEquals( "555", bb.getByPoint( 12 ).get(1) );

        assertEquals( 15, bb.get().size() * 5 );
    }
}
