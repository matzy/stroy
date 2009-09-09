package org.openCage.util.compare;

import junit.framework.TestCase;

import java.util.Comparator;

import org.openCage.util.iterator.T2;

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

public class FirstProjectionComparatorTest extends TestCase {

    public void testA() {
        Comparator<T2<Integer,String>> comp = new FirstProjectionComparator<Integer, String>();

        assertEquals( 0, comp.compare( new T2<Integer,String>(1,"2"),  new T2<Integer,String>(1,"sdfsdf") ));
    }
}
