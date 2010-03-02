package org.openCage.fspath.clazz;

import org.junit.Test;
import org.openCage.fspath.impl.FSPathUnix;
import org.openCage.fspath.protocol.FSPath;

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

// TODO make xplatform save, UNIX only right now
public class FSPathTest {

    @Test
    public void testSimple() {
        FSPath path = FSPathBuilder.getPath("/");

        assertEquals( 0, path.size());
        assertEquals( "/", path.toString());
    }

    @Test
    public void testAdd() {
        FSPath path = FSPathBuilder.getPath("/").add("foo", "bar");

        assertEquals( 2, path.size());
        assertEquals( "/foo/bar", path.toString());
    }

    @Test( expected = IllegalArgumentException.class )
    public void testEmptyPath() {
        FSPathBuilder.getPath( "" );
    }

    @Test
    public void testAbsolute() {
        FSPath path = FSPathBuilder.getPath( "/foo" );
        assertEquals( 1, path.size());

        assertEquals( "/foo", path.toString() );
    }

    @Test
    public void testXDG(){

             System.out.println( System.getenv("$XDG_CONFIG"));
        System.out.println( System.getenv("XDG_CONFIG"));
    }
}
