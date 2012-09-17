package org.openCage.stroy.graph.node;

import junit.framework.TestCase;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.TreeNodes;

import java.util.List;

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

public class SimpleTreeAndNavigationTest extends TestCase {

    public void testSimple() {
        SimpleTreeBuilder b = new SimpleTreeBuilder();

        LindenNode tree = b.d( "f", b.l("a"),
                                          b.d( "g", b.l("b"),
                                                    b.l("c")));
        

        assertFalse( tree.isLeaf());

        LindenDirNode dir = (LindenDirNode)tree;

        assertEquals( 2, dir.getChildren().size() );
    }

    public void testSimpleContent() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        LindenNode tree = b.d( "f", b.l( "a"),
                                                  b.d( "g", b.l("b"),
                                                            b.l("c")));


        assertFalse( tree.isLeaf());

        LindenDirNode dir = (LindenDirNode)tree;

        assertEquals( 2, dir.getChildren().size() );

        assertEquals( "b", TreeNodes.getNode(tree, "g", "b").getContent().getName());
    }


    public void testPath() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        LindenNode tree = b.d( "f", b.l( "a"),
                                                  b.d( "g", b.l("b"),
                                                            b.l("c")));

        List path = TreeNodes.getNamePath(
                TreeNodes.getNode(tree, "g", "c"));

        assertEquals( 3, path.size() );
        assertEquals( "f", path.get(0));
        assertEquals( "g", path.get(1));
        assertEquals( "c", path.get(2));
    }

    public void testRoot() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        LindenNode tree = b.d( "f", b.l( "a"),
                                                  b.d( "g", b.l("b"),
                                                            b.l("c")));


        LindenNode node =
                TreeNodes.getNode(tree, "g", "c");

        assertEquals( tree, TreeNodes.getRoot(node));
    }
}
