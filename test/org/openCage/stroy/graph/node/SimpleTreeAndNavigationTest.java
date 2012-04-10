package org.openCage.stroy.graph.node;

import junit.framework.TestCase;
import org.openCage.stroy.content.ReducedContent;

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

public class SimpleTreeAndNavigationTest extends TestCase {

    public void testSimple() {
        SimpleTreeBuilder<String> b = new SimpleTreeBuilder<String>();

        TreeNode<String> tree = b.d( "f", b.l("a"),
                                          b.d( "g", b.l("b"),
                                                    b.l("c")));
        

        assertFalse( tree.isLeaf());

        TreeDirNode<String> dir = (TreeDirNode<String>)tree;

        assertEquals( 2, dir.getChildren().size() );
    }

    public void testSimpleContent() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> tree = b.d( "f", b.l( "a"),
                                                  b.d( "g", b.l("b"),
                                                            b.l("c")));


        assertFalse( tree.isLeaf());

        TreeDirNode<ReducedContent> dir = (TreeDirNode<ReducedContent>)tree;

        assertEquals( 2, dir.getChildren().size() );

        assertEquals( "b", TreeNodeUtils.getNode( tree, "g", "b" ).getContent().getName());
    }


    public void testPath() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> tree = b.d( "f", b.l( "a"),
                                                  b.d( "g", b.l("b"),
                                                            b.l("c")));

        List<String> path = TreeNodeUtils.getNamePath(
                TreeNodeUtils.getNode( tree, "g", "c"));

        assertEquals( 3, path.size() );
        assertEquals( "f", path.get(0));
        assertEquals( "g", path.get(1));
        assertEquals( "c", path.get(2));
    }

    public void testRoot() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> tree = b.d( "f", b.l( "a"),
                                                  b.d( "g", b.l("b"),
                                                            b.l("c")));


        TreeNode<ReducedContent> node =
                TreeNodeUtils.getNode( tree, "g", "c");

        assertEquals( tree, TreeNodeUtils.getRoot( node ));
    }
}