package org.openCage.stroy.ui;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.dir.doubles2.CompareDirs2;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.ui.difftree.ChangeNumbers;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.difftree.UINodeImpl;
import org.openCage.stroy.filter.Ignore;
import org.openCage.util.iterator.T2;
import com.google.inject.Injector;
import com.google.inject.Guice;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;


/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class CompareWorker2 implements Runnable {



    private TreeMatchingTask<FileContent>    matching1;
    private TreeMatchingTask<FileContent>    matching2;
    private final CompareDirs2               cd;
    private final Ignore ignore;
    private final String second;
    private final String first;
    private final String third;
    private final DirSelector ui;
    private DefaultMutableTreeNode firstRoot;
    private DefaultMutableTreeNode secondRoot;
    private DefaultMutableTreeNode thirdRoot;


    public CompareWorker2( DirSelector ui,
                           Ignore      ignore,
                           String      one,
                           String      two,
                           String      three) {

        Injector injector = Guice.createInjector( new RuntimeModule() );
        cd                = injector.getInstance(CompareDirs2.class );

        this.first  = one;
        this.second = two;

        if ( three == null || three.length() == 0 ) {
            this.third = null;
        } else {
            this.third  = three;
        }

        this.ui     = ui;
        this.ignore = ignore;
    }


    public void run() {

        ui.progress( true );

        match();
        makeDFTNs();
        attribute( firstRoot, matching1 );
        attribute( secondRoot, matching1 );
        if ( thirdRoot != null ) {
            attribute( thirdRoot, matching2 );
        }
        ui.setMatching(matching1, matching2, firstRoot, secondRoot, thirdRoot);

        ui.progress( false );
//        ui.refreshSkyViews();
    }


    private ChangeNumbers attribute( DefaultMutableTreeNode node, TreeMatchingTask<FileContent>  matching ) {

        UINode uin = (UINode)node.getUserObject();
        TreeNode<FileContent> treeNode = uin.get();

        ChangeVector cv  = matching.getChangeVector( treeNode );
        ChangeNumbers cn = new ChangeNumbers( cv );

        if ( !node.isLeaf() ) {
            for ( int idx = 0; idx < node.getChildCount(); ++idx ) {
                cn.add( attribute( (DefaultMutableTreeNode)node.getChildAt( idx ), matching ));
            }
        }

        uin.setChangeNumbers( cn );
        return cn;
    }

    private void makeDFTNs() {
        firstRoot  = addNodes(matching1, matching2, null, matching1.getLeftRoot());
        secondRoot = addNodes(matching1, null, null, matching1.getRightRoot());

        if ( matching2 != null ) {
            thirdRoot = addNodes(matching2, null, null, matching2.getRightRoot());
        }
    }

    private void match() {
        if ( third != null ) {
            T2<TreeMatchingTask<FileContent>, TreeMatchingTask<FileContent>> mm =
                    cd.compare( ignore, new File(first), new File(second), new File(third));
            matching1 = mm.i0;
            matching2 = mm.i1;
        } else {
            matching1 = cd.compare( ignore, new File(first), new File(second));
        }
    }

    private DefaultMutableTreeNode addNodes( TreeMatchingTask<FileContent> matching1,
                                             TreeMatchingTask<FileContent> matching2,
                                             DefaultMutableTreeNode        curTop,
                                             TreeNode<FileContent>         treeNode) {


        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode( treeNode.getContent().getName() );

        UINode uinode = new UINodeImpl(  treeNode, matching1,  matching2 );

        curDir.setUserObject( uinode );


        if (curTop != null) { // should only be null at root
            curTop.add( curDir );
        }

        if ( treeNode.isLeaf() ) {
            return curDir;
        }

        for (TreeNode<FileContent> node : ((TreeDirNode<FileContent>) treeNode).getChildren() ) {

            addNodes( matching1, matching2,  curDir, node );
        }

        return curDir;
    }

}
