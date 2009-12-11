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
        DynamicBitArray dba = new DynamicBitArray();

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
        DynamicBitArray dba = new DynamicBitArray();
        dba.append( true );
        dba.append( false );
        assertEquals( "10", dba.toString());
    }

    @Test
    public void testAppendDba() {
        DynamicBitArray dba = DynamicBitArray.toDba( 128 );
        DynamicBitArray dba2 = DynamicBitArray.toDba( 2 );
        assertEquals( "0000000001", dba.append( dba2 ).toString() );
    }

    @Test
    public void testFromInt() {
        assertEquals( "0", DynamicBitArray.toDba( 0 ).toString());
        assertEquals( "1", DynamicBitArray.toDba( 1 ).toString());
        assertEquals( "01", DynamicBitArray.toDba( 2 ).toString());
        assertEquals( "11", DynamicBitArray.toDba( 3 ).toString());
        assertEquals( "0001", DynamicBitArray.toDba( 8 ).toString());

        assertEquals( "1100", DynamicBitArray.toDba( 3, 4 ).toString());

        assertEquals( 0, DynamicBitArray.toDba( 0 ).toInt(8));
        assertEquals( 1, DynamicBitArray.toDba( 1 ).toInt(8));
        assertEquals( 2, DynamicBitArray.toDba( 2 ).toInt(8));
        assertEquals( 3, DynamicBitArray.toDba( 3 ).toInt(8));
        assertEquals( 8, DynamicBitArray.toDba( 8 ).toInt(8));

        assertEquals( 3, DynamicBitArray.toDba( 3, 4 ).toInt(8));

    }

    @Test
    public void testBitAt() {
        DynamicBitArray dba = DynamicBitArray.toDba( 258 );

        assertEquals( false, dba.bitAt(0));
        assertEquals( true, dba.bitAt(1));
        assertEquals( true, dba.bitAt(8));
    }


}
