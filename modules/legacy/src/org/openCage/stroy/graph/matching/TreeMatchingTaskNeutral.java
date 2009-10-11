package org.openCage.stroy.graph.matching;

import org.openCage.stroy.graph.SameContent;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.graph.iterator.DepthFirstIterable;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.task.MatchingTaskNeutral;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.util.generics.Generics;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openCage.util.logging.Log;

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

// TODO simplify
public class TreeMatchingTaskNeutral implements TreeMatchingTask{

    private static Logger LOG = Logger.getLogger(TreeMatchingTaskNeutral.class.getName());

    private List<SameContent>           dups;
    private MatchingTask<TreeNode>   dirTask;
    private MatchingTask<TreeNode>  fileTask;

    public TreeMatchingTaskNeutral() {
        dups         = new ArrayList<SameContent>();
        dirTask      = new MatchingTaskNeutral<TreeNode>();
        fileTask     = new MatchingTaskNeutral<TreeNode>();
    }


    public void addDup(final SameContent sh) {
        dups.add( sh );
    }

    public MatchingTask<TreeNode> getLeaves() {
        return fileTask;
    }

    public MatchingTask<TreeNode> getDirs() {
        return dirTask;
    }

    public boolean isMatched( final TreeNode obj ) {
        if ( obj.isLeaf() ) {
            return fileTask.isMatched( obj );
        }

        return dirTask.isMatched( obj );
    }

    public TreeNode getMatch( final TreeNode obj ) {

        if ( obj == null ) {
            // ok for ghostnodes
            return null;
        }

        if ( obj.isLeaf() ) {
            return fileTask.getMatch( obj );
        }

        return dirTask.getMatch( obj );
    }

    public double getMatchQuality(TreeNode obj) {
        if ( obj.isLeaf() ) {
            return fileTask.getMatchQuality( obj );
        }

        return dirTask.getMatchQuality( obj );
    }


    public Collection<SameContent> getDuplicates() {
        return dups;
    }


    // TODO
    public void shortStatus() {

        if ( Log.isAtLeast( Level.FINE )) {
            System.out.println("unmatched source dirs  " + dirTask.getUnmatchedLeft().size() );
            System.out.println("unmatched source files " + fileTask.getUnmatchedLeft().size() );
            System.out.println("unmatched target dirs  " + dirTask.getUnmatchedRight().size() );
            System.out.println("unmatched target files " + fileTask.getUnmatchedRight().size() );
        }
    }

    // TODO
    public void status() {
        System.out.println("files deleted");

        for ( final TreeNode lfm : fileTask.getUnmatchedLeft() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("dirs deleted");
        for ( final TreeNode fm : dirTask.getUnmatchedLeft() ) {
            System.out.println("  " + fm );
        }

        System.out.println("files moved");
        for ( final TreeNode lfm : getMovedLeaves() ) {
            System.out.println("  " + lfm.toString() + "->" + fileTask.getMatch( lfm ).getParent() );
        }

        System.out.println("files renamed");
        for ( TreeNode lfm : getRenamedLeaves() ) {
            System.out.println("  " + lfm.toString() + "->" + fileTask.getMatch( lfm ).getContent().getName() );
        }

        System.out.println("dirs moved");
        for ( final TreeNode lfm : getMovedDirs() ) {
            System.out.println("  " + lfm.toString() + "->" + getMatch( lfm ).getParent() );
        }

        System.out.println("dirs renamed");
        for ( TreeNode lfm : getRenamedDirs() ) {
            System.out.println("  " + lfm.toString() + "->" + getMatch( lfm ).getContent().getName() );
        }

        System.out.println("files changed");
        for ( TreeNode lfm : getModifiedLeaves() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("new dirs");
        for ( TreeNode lfm : dirTask.getUnmatchedRight() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("new files");
        for ( TreeNode lfm : fileTask.getUnmatchedRight() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("dirs changed");
        for ( TreeNode lfm : getModifiedDirs() ) {
            System.out.println("  " + lfm );
        }

    }

    public int getLeftLeaveCount() {
        int count = 0;
        for ( TreeNode node : new DepthFirstIterable( getLeftRoot() )) {
            if ( node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public int getLeftDirCount() {
        int count = 0;
        for ( TreeNode node : new DepthFirstIterable( getLeftRoot() )) {
            if ( !node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public int getRightLeaveCount() {
        int count = 0;
        for ( TreeNode node : new DepthFirstIterable( getRightRoot() )) {
            if ( node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public int getRightDirCount() {
        int count = 0;
        for ( TreeNode node : new DepthFirstIterable( getRightRoot() )) {
            if ( !node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public Collection<TreeNode> getModifiedLeaves() {
        final List<TreeNode> modified = new ArrayList<TreeNode>();

        for ( TreeNode lfm : fileTask.getMatchedLeft() ) {

            if ( !fileTask.getDifference( lfm).equals( ContentDiff.same )) {

                if ( !isContentChanged( lfm )) {
                    int i = 0;
                }

                modified.add( lfm );
            }
        }

        return modified;
    }

    public Collection<TreeNode> getRenamedLeaves() {
        return filterRenamed( fileTask.getMatchedLeft() );
    }

    public Collection<TreeNode> getRenamedDirs() {
        return filterRenamed( dirTask.getMatchedLeft() );
    }

    public TreeNode getLeftRoot() {
        return dirTask.getLeftRoot();
    }

    public TreeNode getRightRoot() {
        return dirTask.getRightRoot();
    }

    public boolean isContentChanged(TreeNode obj) {

        if ( !obj.isLeaf() || !isMatched( obj ) ) {
            return false;
        }

        ContentDiff diff = fileTask.getDifference( obj );

        return !diff.equals( ContentDiff.same );

        //return fileTask.getMatchQuality( (TreeNode)obj ) < 1.0;
    }



    public Collection<TreeNode> filterRenamed( Collection<TreeNode> matched ) {
        List<TreeNode> renamed = new ArrayList<TreeNode>();

        for ( TreeNode lfm : matched ) {
            if ( ! lfm.getContent().getName().equals( getMatch( lfm ).getContent().getName() )){
                //noinspection unchecked
                renamed.add( lfm );
            }
        }

        return renamed;
    }

    public Collection<TreeNode> getMovedLeaves() {

        //noinspection unchecked
        return getMoved( new Generics<TreeNode,TreeNode>().cast( fileTask.getMatchedLeft() ));
    }

    public Collection<TreeNode> getMovedDirs() {
        return getMoved( dirTask.getMatchedLeft());
    }


    public Collection<TreeNode> getUnmatchedLeftFiles() {
        return fileTask.getUnmatchedLeft();
    }

    public Collection<TreeNode> getUnmatchedLeftDirs() {
        return dirTask.getUnmatchedLeft();
    }

    public Collection<TreeNode> getUnmatchedRightFiles() {
        return fileTask.getUnmatchedRight();
    }

    public Collection<TreeNode> getUnmatchedRightDirs() {
        return dirTask.getUnmatchedRight();
    }

    private Collection<TreeNode> getMoved( Collection< TreeNode> matched ) {
        List<TreeNode> moved = new ArrayList<TreeNode>();

        for ( final TreeNode node : matched ) {
            TreeNode parent     = node.getParent();
            TreeNode    match      = getMatch( node );

            if ( parent != null ) {
                if ( !dirTask.isMatched( parent )) {
                    moved.add( node );
                } else if ( dirTask.getMatch( parent ) != match.getParent() ) {
                    moved.add( node );
                }
            }
        }

        return moved;
    }

    public Collection<TreeNode> getModifiedDirs() {
        Set<TreeNode> modified = new HashSet<TreeNode>();

        for ( TreeNode node : getRenamedDirs() ) {
            if ( node.getParent() != null ) {
                modified.add( node.getParent() );
            }
        }

        for ( TreeNode node : getRenamedLeaves() ) {
            if ( node.getParent() != null ) {
                modified.add( node.getParent() );
            }
        }

        for ( TreeNode node : getMovedDirs() ) {
            modified.add( node.getParent() );

            TreeNode newParentSrc = dirTask.getMatch( dirTask.getMatch( node ).getParent());

            if ( newParentSrc != null ) {
                modified.add( newParentSrc );
            }
        }

        for ( TreeNode node : getMovedLeaves() ) {
            modified.add( node.getParent() );

            TreeNode newParentSrc = dirTask.getMatch( fileTask.getMatch( node ).getParent());

            if ( newParentSrc != null ) {
                modified.add( newParentSrc );
            }
        }

        for (TreeNode node : dirTask.getUnmatchedLeft() ) {
            modified.add( node.getParent() );
        }

        for (TreeNode node : fileTask.getUnmatchedLeft() ) {
            modified.add( node.getParent() );
        }

        for (TreeNode node : dirTask.getUnmatchedRight() ) {
            TreeNode parentSrc = dirTask.getMatch( node.getParent());

            if ( parentSrc != null ) {
                modified.add( parentSrc );
            }
        }

        for (TreeNode node : fileTask.getUnmatchedRight() ) {
            TreeNode parentSrc = dirTask.getMatch( node.getParent());

            if ( parentSrc != null ) {
                modified.add( parentSrc );
            }
        }

        return modified;
    }

    public ChangeVector getChangeVector(TreeNode obj) {

        ChangeVector cv = new ChangeVector();

        TreeNode match = getMatch( obj );

        if ( match == null ) {
            cv.only = true;

            return cv;
        }

        if ( obj.isLeaf() ) {
            cv.content =  fileTask.getDifference( obj );
        }

        cv.name = ! match.getContent().getName().equals( obj.getContent().getName());

        TreeNode parent     = obj.getParent();

        if ( parent != null ) {
            if ( !dirTask.isMatched( parent )) {
                cv.parent = true;
            } else if ( dirTask.getMatch( parent ) != match.getParent() ) {
                cv.parent = true;
            } else if ( !parent.getContent().getName().equals( match.getParent().getContent().getName() ) ) {
                cv.parentRenamed = true;
            }
        }


        return cv;
    }

    public void remove(TreeNode treeNode) {
        if ( treeNode.isLeaf() ) {
            fileTask.remove( treeNode );
        } else {
            dirTask.remove( treeNode );
        }
    }

    public void merge() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<TreeNode> getComplexModifiedLeaves() {
        Collection<TreeNode> struct = getMovedLeaves();

        struct.addAll( getRenamedLeaves() );

        List<TreeNode> ret = new ArrayList<TreeNode>();

        for ( TreeNode lfm : struct ) {
            if ( fileTask.getMatchQuality( lfm ) < 1.0 ) {
                ret.add( lfm );
            }
        }

        return ret;
    }


    public MatchingTask<TreeNode> getDirTask() {
        return dirTask;
    }

    public MatchingTask<TreeNode> getFileTask() {
        return fileTask;
    }

}
