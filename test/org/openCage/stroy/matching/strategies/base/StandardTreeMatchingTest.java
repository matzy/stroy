package org.openCage.stroy.matching.strategies.base;

import junit.framework.TestCase;
import org.openCage.stroy.tree.str.StringNoedBuilder;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.matching.TreeTask;
import org.openCage.stroy.matching.TreeTaskImpl;
import org.openCage.stroy.matching.Tasks;

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
public class StandardTreeMatchingTest extends TestCase {

    public void testSame() {
        StringNoedBuilder b = new StringNoedBuilder();

        Noed left = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));

        Noed right = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));


        final TreeTask tt = new TreeTaskImpl( left, right );

        new StandardTreeMatching().match( tt );

        assertEquals( 0, tt.getLeft( Tasks.isUnmatched ).size());

        assertEquals( 0, tt.getRight( Tasks.isUnmatched ).size());

    }

    public void testOneMore() {
        StringNoedBuilder b = new StringNoedBuilder();

        Noed left = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));

        Noed right = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"),
                                b.l( "c", "t1", "ccc"));


        final TreeTask tt = new TreeTaskImpl( left, right );

        new StandardTreeMatching().match( tt );

        assertEquals( 0, tt.getLeft( Tasks.isUnmatched ).size());

        assertEquals( 1, tt.getRight( Tasks.isUnmatched ).size());
    }

    public void testMove() {
        StringNoedBuilder b = new StringNoedBuilder();

        Noed left = b.d( "foo", b.l("a", "t1", "aaa"),
                                b.l("b", "t1", "bbb"));

        Noed right = b.d( "foo", b.l("a", "t1", "aaa"),
                                 b.d( "extra",
                                    b.l("b", "t1", "bbb")));



        final TreeTask tt = new TreeTaskImpl( left, right );

        new StandardTreeMatching().match( tt );

        assertEquals( 1, tt.getLeft( Tasks.isUnmatched ).size());
        assertEquals( 2, tt.getRight( Tasks.isUnmatched ).size());
    }
}
