package org.openCage.stroy.ui.popup;

import junit.framework.TestCase;
import org.openCage.stroy.content.ReducedContent;
import org.openCage.vfs.impl.SimpleStringTreeBuilder;
import org.openCage.vfs.protocol.VNode;
import org.openCage.vfs.impl.SimpleTreeNode;
import org.openCage.stroy.dir.LeafTreeNodeImpl;
import com.muchsoft.util.Sys;

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

public class DiffPopupDeciderTest extends TestCase {

    public void testShowOpenLeave() {

        VNode leave = new SimpleStringTreeBuilder().l( "foo.jpg");
        assertTrue( new DiffPopupDecider().showOpen( leave ));
    }

    public void testShowOpenLeaveWithoutContetn() {

        VNode leave = new SimpleTreeNode( null );
        assertFalse( new DiffPopupDecider().showOpen( leave ));
    }

    public void testShowOpenDir() {

        VNode leave = new SimpleStringTreeBuilder().l( "foo");
        VNode dir = new SimpleStringTreeBuilder().d( "dd", leave);

        assertFalse( new DiffPopupDecider().showOpen( dir ));
    }

    public void testShowOpenBundle() {

        VNode leave = new SimpleStringTreeBuilder().l( "foo");
        VNode dir = new SimpleStringTreeBuilder().d( "dd.app", leave);

        assertEquals( Sys.isMacOSX(), new DiffPopupDecider().showOpen( dir ));
    }

    public void testShowOpenAsTextLeave() {

        VNode leave = new SimpleStringTreeBuilder().l( "foo");
        assertTrue( new DiffPopupDecider().showOpenAsText( leave ));
    }

    public void testShowOpenAsTextLeaveWithoutContetn() {

        VNode leave = new SimpleTreeNode( null );
        assertFalse( new DiffPopupDecider().showOpenAsText( leave ));
    }

    public void testShowOpenAsTextDir() {

        VNode leave = new SimpleStringTreeBuilder().l( "foo");
        VNode dir = new SimpleStringTreeBuilder().d( "dd", leave);

        assertFalse( new DiffPopupDecider().showOpenAsText( dir ));
    }

    public void testShowOpenAsTextBundle() {

        VNode leave = new SimpleStringTreeBuilder().l( "foo");
        VNode dir = new SimpleStringTreeBuilder().d( "dd.app", leave);

        assertFalse( new DiffPopupDecider().showOpenAsText( dir ));
    }

}
