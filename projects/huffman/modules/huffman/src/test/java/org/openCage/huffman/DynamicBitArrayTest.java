package org.openCage.huffman;

import org.junit.Test;

import java.nio.charset.Charset;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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

public class DynamicBitArrayTest {

    @Test
    public void testSize() {
        BitField dba = new DynamicBitArrayDirect();

        assertEquals( 0, dba.size() );

        dba.append( true );
        assertEquals( 1, dba.size() );

        dba.append( false );
        assertEquals( 2, dba.size() );

        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        assertEquals( 10, dba.size() );

    }

    @Test
    public void testAppend() {
        DynamicBitArrayDirect dba = new DynamicBitArrayDirect();
        dba.append( true );
        dba.append( false );
        assertEquals( "10", dba.toString());
    }

    @Test
    public void testAppendDba() {
        BitField dba = DynamicBitArrayDirect.toDba( 128 );
        BitField dba2 = DynamicBitArrayDirect.toDba( 2 );
        assertEquals( "0000000001", dba.append( dba2 ).toString() );
    }

    @Test
    public void testFromInt() {
        assertEquals( "0", DynamicBitArrayDirect.toDba( 0 ).toString());
        assertEquals( "1", DynamicBitArrayDirect.toDba( 1 ).toString());
        assertEquals( "01", DynamicBitArrayDirect.toDba( 2 ).toString());
        assertEquals( "11", DynamicBitArrayDirect.toDba( 3 ).toString());
        assertEquals( "0001", DynamicBitArrayDirect.toDba( 8 ).toString());

        assertEquals( "1100", DynamicBitArrayDirect.toDba( 3, 4 ).toString());

        assertEquals( 0, DynamicBitArrayDirect.toDba( 0 ).getInt(8));
        assertEquals( 1, DynamicBitArrayDirect.toDba( 1 ).getInt(8));
        assertEquals( 2, DynamicBitArrayDirect.toDba( 2 ).getInt(8));
        assertEquals( 3, DynamicBitArrayDirect.toDba( 3 ).getInt(8));
        assertEquals( 8, DynamicBitArrayDirect.toDba( 8 ).getInt(8));

        assertEquals( 3, DynamicBitArrayDirect.toDba( 3, 4 ).getInt(8));

    }

    @Test
    public void testBitAt() {
        BitField dba = DynamicBitArrayDirect.toDba( 258 );

        assertEquals( false, dba.get(0));
        assertEquals( true, dba.get(1));
        assertEquals( true, dba.get(8));
    }

    @Test
    public void testCompare() {
        BitField one = DynamicBitArrayDirect.toDba( 1 );
        BitField two = DynamicBitArrayDirect.toDba( 2 );
        BitField three = DynamicBitArrayDirect.toDba( 3 );

        assertEquals( -1, one.compareTo(two));
        assertEquals( -1, one.compareTo(three));
        assertEquals( -1, two.compareTo(three));

        BitField a = DynamicBitArrayDirect.toDba( 1000 );
        BitField b = DynamicBitArrayDirect.toDba( 1010 );

        assertEquals( -1, a.compareTo(b));
    }

    @Test
    public void testCompareChar() {
        BitField a = DynamicBitArrayDirect.valueOf( "a".getBytes(Charset.forName("utf8")));
        BitField b = DynamicBitArrayDirect.valueOf( "b".getBytes(Charset.forName("utf8")));

        System.out.println( "a = " + a );
        System.out.println( "b = " + b );

        assertTrue( a.compareTo(b) < 0 );
    }


    @Test
    public void testEquals() {
        assertEquals( DynamicBitArrayDirect.toDba( 1234567), DynamicBitArrayDirect.toDba( 1234567) );

        assertEquals( DynamicBitArrayDirect.toDba( 1234567).hashCode(), DynamicBitArrayDirect.toDba( 1234567).hashCode() );
    }


    @Test
    public void testForCanonical() {
        BitField bf = new DynamicBitArrayDirect();

        bf.append(false);
        bf.append(false);
        bf.append(false);

        assertEquals( "000", bf.toString());


        assertEquals( "110", DynamicBitArrayDirect.valueOf("101").clonePlusOne().toString());
        assertEquals( "1000", DynamicBitArrayDirect.valueOf("111").clonePlusOne().toString());
        assertEquals( "100001001000", DynamicBitArrayDirect.valueOf("100001000111").clonePlusOne().toString());

        BitField count = new DynamicBitArrayDirect();
        for ( int i = 0; i < 300; i++ ) {
            System.out.println(count);

            count = count.clonePlusOne();
        }
    }

    @Test
    public void testInternalLast() {
        DynamicBitArrayDirect bf = new DynamicBitArrayDirect();

        assertEquals(0, bf.internalGetLast());

        bf.append(true);
        assertEquals(1, bf.internalGetLast());
        bf.append(true);
        assertEquals(2, bf.internalGetLast());
        bf.append(true);
        assertEquals(3, bf.internalGetLast());
        bf.append(true);
        assertEquals(4, bf.internalGetLast());
        bf.append(true);
        assertEquals(5, bf.internalGetLast());
        bf.append(true);
        assertEquals(6, bf.internalGetLast());
        bf.append(true);
        assertEquals(7, bf.internalGetLast());
        bf.append(true);
        assertEquals(0, bf.internalGetLast());
    }


}
