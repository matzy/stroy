package org.openCage.stroy.app;

import junit.framework.TestCase;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;

import java.util.Arrays;

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

@SuppressWarnings({"HardCodedStringLiteral"})
public class TasksTest extends TestCase {

    public void testRootsSanity() {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        LindenNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        LindenNode treeRight = b.d( "f", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
        TreeMatchingTask task2 =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );


        try {
            new Tasks( Arrays.asList( task, task2 ));
            fail( "roots don't match, throw expected" );
        } catch ( Exception exp ) {
            // expected
        }
    }

    public void testRootSanityPositiv() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        LindenNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        LindenNode treeRight = b.d( "f2", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));
        LindenNode tree3 = b.d( "f3", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
        TreeMatchingTask task2 =
                TreeMatchingTaskNeutralBuilder.build( treeRight, tree3 );


        new Tasks( Arrays.asList( task, task2 ));

    }


}
