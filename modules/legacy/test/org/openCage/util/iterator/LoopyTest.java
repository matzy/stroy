package org.openCage.util.iterator;

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

public class LoopyTest extends TestCase {

    public void testTwo() {
        List<String>  one = Arrays.asList( "1", "2", "3", "4"  );
        List<Integer> two = Arrays.asList( 1,2,3 );

        int sum = 0;

        Loopy<Integer> ii = Loopy.c(two);
        for ( String str : one ) if ( ii.next() ) {
            System.out.println( str + " " + ii.o );
            sum += ii.o;
        }

        assertEquals( 6, sum );


    }

//    public void testWhileAll() {
//
//        public class WhileAll( Iter)
//
//    }
}
