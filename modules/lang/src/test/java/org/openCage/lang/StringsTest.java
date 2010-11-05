package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.functions.F1;

import java.util.*;

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

public class StringsTest {

    @Test
    public void testJoinNonStrings() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1, 2, 3", Strings.join( ints ).toString());
    }

    @Test
    public void testPrefix() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "pre1, 2, 3", Strings.join( ints ).prefix("pre").toString());

        assertEquals( "", Strings.join( new ArrayList<Integer>()).prefix("pre").toString());
//        assertEquals( "", Strings.join( null).prefix("pre").toString());
    }

    @Test
    public void testSep() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1-2-3", Strings.join( ints ).separator("-").toString());
    }

    @Test
    public void testTrans() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "2, 4, 6", Strings.join( ints ).trans( new F1<String, Integer>() {
            @Override
            public String call(Integer integer) {
                return new Integer(integer * 2).toString();
            }
        }).toString());

    }

    @Test
    public void testFilter() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1, 3", Strings.join( ints ).skip( new F1<Boolean, Integer>() {
            @Override
            public Boolean call(Integer integer) {
                return integer.intValue() == 2;
            }
        }).toString());

        assertEquals( "", Strings.join( ints ).skip( new F1<Boolean, Integer>() {
            @Override
            public Boolean call(Integer integer) {
                return integer.intValue() != 4;
            }
        }).postfix("wohahah").toString());

    }

    @Test
    public void testArray() {
        String[] strs = new String[3];
        strs[0] = "a";
        strs[1] = "b";
        strs[2] = "c";

        assertEquals( "a, b, c", Strings.join( strs ).toString() );
    }

    @Test
    public void testSet() {
        Set<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");

        assertEquals( "a, b", Strings.join( set ).order().toString());
    }
}
