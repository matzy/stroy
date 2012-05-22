package org.openCage.stroy.graph.iterator;

import junit.framework.TestCase;
import org.openCage.stroy.graph.node.SimpleTreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
