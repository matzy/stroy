package org.openCage.stroy.matching;

import junit.framework.TestCase;
import org.openCage.stroy.tree.str.StringNoedBuilder;
import org.openCage.stroy.algo.tree.Noed;

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

public class TreeTaskImplTest extends TestCase {

    public void testRootMatch() {
        StringNoedBuilder b = new StringNoedBuilder();

        Noed left = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));

        Noed right = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));


        TreeTask tt = new TreeTaskImpl( left, right );

        assertNotNull( tt );
        assertEquals( right, tt.getMatch( left ));
    }

    public void testAttributes() {
        StringNoedBuilder b = new StringNoedBuilder();

        Noed left = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));

        Noed right = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));


        TreeTask tt = new TreeTaskImpl( left, right );

        assertNull(  tt.getEdgeAttributes( left.getChildren().iterator().next() ));
        assertNotNull( tt.getEdgeAttributes( left ));

    }
}
