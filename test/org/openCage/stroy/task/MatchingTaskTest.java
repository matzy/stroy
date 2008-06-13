package org.openCage.stroy.task;

import junit.framework.TestCase;
import org.openCage.stroy.Difference;
import org.openCage.stroy.diff.ContentDiff;

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

public class MatchingTaskTest extends TestCase {

    public void testSimpleMatch() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();


        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 0, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 0, task.getUnmatchedRight().size() );

        String left1 = "left1";
        task.addLeft( left1 );
        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 1, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 0, task.getUnmatchedRight().size() );

        String left2 = "left2";
        task.addLeft( left2 );
        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 2, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 0, task.getUnmatchedRight().size() );


        String right1 = "right1";
        task.addRight( right1 );
        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 2, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 1, task.getUnmatchedRight().size() );


        task.match( left1, right1, 0.3 );
        assertEquals( 1, task.getMatchedLeft().size() );
        assertEquals( 1, task.getUnmatchedLeft().size() );
        assertEquals( 1, task.getMatchedRight().size() );
        assertEquals( 0, task.getUnmatchedRight().size() );

        assertEquals( 0.3, task.getMatchQuality( left1 ));
        assertEquals( 0.3, task.getMatchQuality( right1 ));

        assertEquals( right1, task.getMatch( left1 ));
        assertEquals( left1, task.getMatch( right1 ));

        assertTrue( task.isMatched( left1 ));
        assertFalse( task.isMatched( left2 ));
        assertTrue( task.isMatched( right1 ));
    }

    public void testBreakLeft() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left1 = "left1";
        task.addLeft( left1 );
        String left2 = "left2";
        task.addLeft( left2 );
        String right1 = "right1";
        task.addRight( right1 );
        task.match( left1, right1, 0.3 );

        task.breakMatch( left1 );

        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 2, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 1, task.getUnmatchedRight().size() );

    }

    public void testBreakRight() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left1 = "left1";
        task.addLeft( left1 );
        String left2 = "left2";
        task.addLeft( left2 );
        String right1 = "right1";
        task.addRight( right1 );
        task.match( left1, right1, 0.3 );

        task.breakMatch( right1 );

        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 2, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 1, task.getUnmatchedRight().size() );
    }

    public void testRemoveLeft() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left1 = "left1";
        task.addLeft( left1 );
        String left2 = "left2";
        task.addLeft( left2 );
        String right1 = "right1";
        task.addRight( right1 );
        task.match( left1, right1, 0.3 );

        task.remove( left1);

        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 1, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 1, task.getUnmatchedRight().size() );

    }

    public void testRemoveRight() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left1 = "left1";
        task.addLeft( left1 );
        String left2 = "left2";
        task.addLeft( left2 );
        String right1 = "right1";
        task.addRight( right1 );
        task.match( left1, right1, 0.3 );

        task.remove( right1 );

        assertEquals( 0, task.getMatchedLeft().size() );
        assertEquals( 2, task.getUnmatchedLeft().size() );
        assertEquals( 0, task.getMatchedRight().size() );
        assertEquals( 0, task.getUnmatchedRight().size() );

    }

    public void testDoubleMatch() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left1 = "left1";
        task.addLeft( left1 );
        String left2 = "left2";
        task.addLeft( left2 );
        String right1 = "right1";
        task.addRight( right1 );
        task.match( left1, right1, 0.3 );

        try {
            task.match( left2, right1, 0.1 );
            fail( "one node is already matched, should fail" );
        } catch ( Exception exp ) {
            // expected
        }
    }

    public void testLeftUnmatched() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left1 = "left1";
        task.addLeft( left1 );

        assertFalse( task.isMatched( left1 ));
        assertNull( task.getMatch( left1) );
    }

    public void testRightUnmatched() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String right = "right";
        task.addRight(right);
        
        assertFalse( task.isMatched(right));
        assertNull( task.getMatch(right) );
    }

    public void testGetSetDifference() {
        MatchingTask<String> task = new MatchingTaskNeutral<String>();
        String left = "left";
        task.addLeft( left );
        String right = "right";
        task.addRight(right);
        task.match( left, right, .7 );

        task.setDifference( right, ContentDiff.sameEnough );
        assertEquals( ContentDiff.sameEnough, task.getDifference( left));
    }
}
