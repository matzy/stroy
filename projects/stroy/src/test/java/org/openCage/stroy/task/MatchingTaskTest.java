package org.openCage.stroy.task;

import junit.framework.TestCase;
import org.openCage.stroy.diff.ContentDiff;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
