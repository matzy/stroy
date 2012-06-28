package org.openCage.stroy.graph.iterator;

import junit.framework.TestCase;
import org.openCage.stroy.graph.node.SimpleTreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;

import java.util.ArrayList;
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
public class TreeIteratorTest extends TestCase {

    public void testSimple() {
        List<TreeNode<Integer>> one = new ArrayList<TreeNode<Integer>>();

        for ( int i = 0; i < 5; ++i ) {
            one.add( new SimpleTreeNode<Integer>( i ));
        }

        List<TreeNode<Integer>> two = new ArrayList<TreeNode<Integer>>();
        for ( int i = 20; i < 30; ++i ) {
            two.add( new SimpleTreeNode<Integer>( i ));
        }

        TreeDirNode<Integer> td = new SimpleTreeNode<Integer>( 15, two );
        one.add( td );

        for ( int i = 5; i < 10; ++i ) {
            one.add( new SimpleTreeNode<Integer>( i ));
        }


        TreeDirNode<Integer> root = new SimpleTreeNode<Integer>( 100, one );



        for ( TreeNode<Integer> node : new DepthFirstIterable<Integer>(root)) {
            System.out.println( node );
        }


    }
}
