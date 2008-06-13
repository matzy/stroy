package org.openCage.util.compare;

import junit.framework.TestCase;
import org.openCage.util.compare.ForeignSortedTreeSet;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class TestForeignSortedTreeSet extends TestCase {

    public void testOne() {
        ForeignSortedTreeSet<Double, String > set = new ForeignSortedTreeSet<Double,String>();

        set.add( 3.0, "a" );
        set.add( 1.1, "b" );
        set.add( -4.44, "c" );
        set.add( -2.0, "d" );
        set.add( 0.02, "e" );

        assertEquals( "c", set.iterator().next());
    }
}
