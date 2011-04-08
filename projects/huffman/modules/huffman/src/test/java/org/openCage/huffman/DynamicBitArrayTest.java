package org.openCage.huffman;

import org.junit.Test;

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

public class DynamicBitArrayTest {

    @Test
    public void testSize() {
        DynamicBitArray dba = new DynamicBitArrayDirect();

        assertEquals( 0, dba.getBitSize() );

        dba.append( true );
        assertEquals( 1, dba.getBitSize() );

        dba.append( false );
        assertEquals( 2, dba.getBitSize() );

        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        dba.append( true );
        assertEquals( 10, dba.getBitSize() );

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
        DynamicBitArray dba = DynamicBitArrayDirect.toDba( 128 );
        DynamicBitArray dba2 = DynamicBitArrayDirect.toDba( 2 );
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

        assertEquals( 0, DynamicBitArrayDirect.toDba( 0 ).toInt(8));
        assertEquals( 1, DynamicBitArrayDirect.toDba( 1 ).toInt(8));
        assertEquals( 2, DynamicBitArrayDirect.toDba( 2 ).toInt(8));
        assertEquals( 3, DynamicBitArrayDirect.toDba( 3 ).toInt(8));
        assertEquals( 8, DynamicBitArrayDirect.toDba( 8 ).toInt(8));

        assertEquals( 3, DynamicBitArrayDirect.toDba( 3, 4 ).toInt(8));

    }

    @Test
    public void testBitAt() {
        DynamicBitArray dba = DynamicBitArrayDirect.toDba( 258 );

        assertEquals( false, dba.bitAt(0));
        assertEquals( true, dba.bitAt(1));
        assertEquals( true, dba.bitAt(8));
    }

    @Test
    public void testCompare() {
        DynamicBitArray one = DynamicBitArrayDirect.toDba( 1 );
        DynamicBitArray two = DynamicBitArrayDirect.toDba( 2 );
        DynamicBitArray three = DynamicBitArrayDirect.toDba( 3 );

        assertEquals( -1, one.compareTo(two));
        assertEquals( -1, one.compareTo(three));
        assertEquals( -1, two.compareTo(three));

        DynamicBitArray a = DynamicBitArrayDirect.toDba( 1000 );
        DynamicBitArray b = DynamicBitArrayDirect.toDba( 1010 );

        assertEquals( -1, a.compareTo(b));
    }

    @Test
    public void testEquals() {
        assertEquals( DynamicBitArrayDirect.toDba( 1234567), DynamicBitArrayDirect.toDba( 1234567) );

        assertEquals( DynamicBitArrayDirect.toDba( 1234567).hashCode(), DynamicBitArrayDirect.toDba( 1234567).hashCode() );
    }
}
