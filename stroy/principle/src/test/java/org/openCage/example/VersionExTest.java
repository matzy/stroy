package org.openCage.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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

public class VersionExTest {

    @Test
    public void testParse() {
        assertEquals( new VersionEx(0,123,456), VersionEx.parseVersionEx( "0.123.456" ));
    }



    @Test
    public void testParse2() {
        assertEquals( new VersionEx(0,123,456,789), VersionEx.parseVersionEx( "0.123.456.789" ));
    }

    @Test
    public void testCompare() {
        assertTrue( new VersionEx( 0,10,200).compareTo( new VersionEx( 0,11,0,10)) < 0  );
        assertTrue( new VersionEx( 0,10,11).compareTo( new VersionEx( 0,10,200 )) < 0  );
        assertTrue( new VersionEx( 0,11,0,42).compareTo( new VersionEx( 0,11,1,10)) < 0  );
        assertTrue( new VersionEx( 0,10,20,4242).compareTo( new VersionEx( 1,0,1,1001)) < 0  );
        assertTrue( new VersionEx( 0,10,20,4242).compareTo( new VersionEx( 0,11,1,1001)) < 0  );

        // !! old and new VersionEx  number
        assertTrue( new VersionEx( 1,20,200).compareTo( new VersionEx( 0,0,0,1)) < 0  );

    }
}
