package org.openCage.stroy.filter;

import junit.framework.TestCase;

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

public class IgnoreByListsTest extends TestCase {

    public void testSimple() {
        Ignore ig = new IgnoreByLists();


        ig.setExtensions( Arrays.asList( "class", "svn"));

        assertTrue( ig.match( "foo.class"));
        assertTrue( ig.match( "/foo.class"));
        assertTrue( ig.match( "/a/b//c/foo.class"));
    }

    public void testGetDate() {
        Ignore ig = new IgnoreByLists();

        //ig.setPaths( Arrays.asList( ".*get_date\\.dat" ));
//        ig.setPatterns( Arrays.asList("(.*\\.svn)|(.*\\.DS_Store)|(.*\\.class)|(.*\\.o)|(.*\\.o)|(.*/get_date\\.dat)|(.*/copyarea\\.db)|(.*/copyarea\\.dat)|(.*/vssver\\.scc)|(.*/vssver2\\.scc)|(.*/CVS)|(.*/SCCS)|(.*/RCS)|(.*/rcs)" ));
        ig.setPatterns( Arrays.asList(".*\\.svn", ".*\\.class", ".*/\\.get_date\\.dat",".*/copyarea\\.dat"));
//        assertTrue( ig.match( "/stroy-2/src/.get_date.dat"));
        assertTrue( ig.match( "/stroy-2/src/.get_date.dat"));

    }
}
