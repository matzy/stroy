package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.count.ArrayCount;
import org.openCage.lang.count.ByteCount;
import org.openCage.lang.count.Count;

import java.util.Arrays;
import java.util.List;

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
