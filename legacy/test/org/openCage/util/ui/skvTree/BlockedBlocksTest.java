package org.openCage.util.ui.skvTree;

import junit.framework.TestCase;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
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
