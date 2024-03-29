package org.openCage.util.collection;

import junit.framework.TestCase;

import java.util.Map;

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

public class MapsTest extends TestCase {

    public void testCreate() {
        Map<String, Integer> map = Maps.create( "1", 1 ).put( "2", 2 ).get();

        assertEquals( 2, map.size() );
        assertEquals( new Integer(1), map.get("1"));
        assertEquals( new Integer(2), map.get("2"));
    }

    public void testBuilder() {
        Map< String, Integer> map = MapF.c( "1", 1).a( "2", 2).get();

        assertEquals( 2, map.size() );
        assertEquals( new Integer(1), map.get("1"));
        assertEquals( new Integer(2), map.get("2"));

    }

}
