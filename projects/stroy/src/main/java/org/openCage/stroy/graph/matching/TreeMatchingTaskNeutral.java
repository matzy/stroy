package org.openCage.stroy.graph.matching;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.task.MatchingTaskNeutral;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.diff.ContentDiff;

import java.util.*;

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

// TODO simplify
public class TreeMatchingTaskNeutral implements TreeMatchingTask {

    private List<SameContent>           dups;
    private MatchingTask<LindenDirNode>   dirTask;
    private MatchingTask<LindenNode>  fileTask;

    public TreeMatchingTaskNeutral() {
        dups         = new ArrayList<SameContent>();
        dirTask      = new MatchingTaskNeutral<LindenDirNode>();
        fileTask     = new MatchingTaskNeutral<LindenNode>();
    }


    public void addDup(final SameContent sh) {
        dups.add( sh );
    }

    public MatchingTask<LindenNode> getLeaves() {
        return fileTask;
    }

    public MatchingTask<LindenDirNode> getDirs() {
        return dirTask;
    }

    public boolean isMatched( final LindenNode obj ) {
        if ( obj.isLeaf() ) {
            return fileTask.isMatched( (LindenNode)obj );
        }

        return dirTask.isMatched( (LindenDirNode)obj );
    }

    public LindenNode getMatch( final LindenNode obj ) {

        if ( obj == null ) {
//            Log.warning( "getMatch called for null" );
            return null;
        }

        if ( obj.isLeaf() ) {
            return fileTask.getMatch( (LindenNode)obj );
        }

        return dirTask.getMatch( (LindenDirNode)obj );
    }

    public double getMatchQuality(LindenNode obj) {
        if ( obj.isLeaf() ) {
            return fileTask.getMatchQuality( (LindenNode)obj );
        }

        return dirTask.getMatchQuality( (LindenDirNode)obj );
    }


    public Collection<SameContent> getDuplicates() {
        return dups;
    }


    // TODO
    public void shortStatus() {

//        if ( Log.isAtLeast( Level.FINE )) {
//            System.out.println("unmatched source dirs  " + dirTask.getUnmatchedLeft().size() );
//            System.out.println("unmatched source files " + fileTask.getUnmatchedLeft().size() );
//            System.out.println("unmatched target dirs  " + dirTask.getUnmatchedRight().size() );
//            System.out.println("unmatched target files " + fileTask.getUnmatchedRight().size() );
//        }
    }

    // TODO
    public void status() {
        System.out.println("files deleted");

        for ( final LindenNode lfm : fileTask.getUnmatchedLeft() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("dirs deleted");
        for ( final LindenDirNode fm : dirTask.getUnmatchedLeft() ) {
            System.out.println("  " + fm );
        }

        System.out.println("files moved");
        for ( final LindenNode lfm : getMovedLeaves() ) {
            System.out.println("  " + lfm.toString() + "->" + fileTask.getMatch( lfm ).getParent() );
        }

        System.out.println("files renamed");
        for ( LindenNode lfm : getRenamedLeaves() ) {
            System.out.println("  " + lfm.toString() + "->" + fileTask.getMatch( lfm ).getContent().getName() );
        }

        System.out.println("dirs moved");
        for ( final LindenDirNode lfm : getMovedDirs() ) {
            System.out.println("  " + lfm.toString() + "->" + getMatch( lfm ).getParent() );
        }

        System.out.println("dirs renamed");
        for ( LindenDirNode lfm : getRenamedDirs() ) {
            System.out.println("  " + lfm.toString() + "->" + getMatch( lfm ).getContent().getName() );
        }

        System.out.println("files changed");
        for ( LindenNode lfm : getModifiedLeaves() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("new dirs");
        for ( LindenDirNode lfm : dirTask.getUnmatchedRight() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("new files");
        for ( LindenNode lfm : fileTask.getUnmatchedRight() ) {
            System.out.println("  " + lfm );
        }

        System.out.println("dirs changed");
        for ( LindenDirNode lfm : getModifiedDirs() ) {
            System.out.println("  " + lfm );
        }

    }

    public int getLeftLeaveCount() {
        int count = 0;
        for ( LindenNode node : getLeftRoot() ) {
            if ( node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public int getLeftDirCount() {
        int count = 0;
        for ( LindenNode node : getLeftRoot() ) {
            if ( !node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public int getRightLeaveCount() {
        int count = 0;
        for ( LindenNode node : getRightRoot() ) {
            if ( node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public int getRightDirCount() {
        int count = 0;
        for ( LindenNode node : getRightRoot() ) {
            if ( !node.isLeaf() ) {
                count++;
            }
        }
        return count;
    }

    public Collection<LindenNode> getModifiedLeaves() {
        final List<LindenNode> modified = new ArrayList<LindenNode>();

        for ( LindenNode lfm : fileTask.getMatchedLeft() ) {

            if ( !fileTask.getDifference( lfm).equals( ContentDiff.same )) {

                if ( !isContentChanged( lfm )) {
                    int i = 0;
                }

                modified.add( lfm );
            }
        }

        return modified;
    }

    public Collection<LindenNode> getRenamedLeaves() {
        //noinspection unchecked
        return filterRenamed( (Collection) fileTask.getMatchedLeft() );
    }

    public Collection<LindenDirNode> getRenamedDirs() {
        //noinspection unchecked
        return filterRenamed( (Collection) dirTask.getMatchedLeft() );
    }

    public LindenDirNode getLeftRoot() {
        return dirTask.getLeftRoot();
    }

    public LindenDirNode getRightRoot() {
        return dirTask.getRightRoot();
    }

    public boolean isContentChanged(LindenNode obj) {

        if ( !obj.isLeaf() || !isMatched( obj ) ) {
            return false;
        }

        ContentDiff diff = fileTask.getDifference( (LindenNode)obj );

        return !diff.equals( ContentDiff.same );

        //return fileTask.getMatchQuality( (LindenNode)obj ) < 1.0;
    }



    public Collection filterRenamed( Collection<LindenNode> matched ) {
        List renamed = new ArrayList();

        for ( LindenNode lfm : matched ) {
            if ( ! lfm.getContent().getName().equals( getMatch( lfm ).getContent().getName() )){
                //noinspection unchecked
                renamed.add( lfm );
            }
        }

        return renamed;
    }

    public Collection<LindenNode> getMovedLeaves() {

        return getMoved( fileTask.getMatchedLeft() );
    }

    public Collection<LindenDirNode> getMovedDirs() {

        return getMoved(dirTask.getMatchedLeft());
    }


    public Collection<LindenNode> getUnmatchedLeftFiles() {
        return fileTask.getUnmatchedLeft();
    }

    public Collection<LindenDirNode> getUnmatchedLeftDirs() {
        return dirTask.getUnmatchedLeft();
    }

    public Collection<LindenNode> getUnmatchedRightFiles() {
        return fileTask.getUnmatchedRight();
    }

    public Collection<LindenDirNode> getUnmatchedRightDirs() {
        return dirTask.getUnmatchedRight();
    }

    private Collection getMoved( Collection<? extends LindenNode> matched ) {
        List moved = new ArrayList();

        for ( final LindenNode node : matched ) {
            LindenDirNode parent     = node.getParent();
            LindenNode match      = getMatch( node );

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

    public Collection<LindenDirNode> getModifiedDirs() {
        Set<LindenDirNode> modified = new HashSet<LindenDirNode>();

        for ( LindenDirNode node : getRenamedDirs() ) {
            if ( node.getParent() != null ) {
                modified.add( node.getParent() );
            }
        }

        for ( LindenNode node : getRenamedLeaves() ) {
            if ( node.getParent() != null ) {
                modified.add( node.getParent() );
            }
        }

        for ( LindenDirNode node : getMovedDirs() ) {
            modified.add( node.getParent() );

            LindenDirNode newParentSrc = dirTask.getMatch( dirTask.getMatch( node ).getParent());

            if ( newParentSrc != null ) {
                modified.add( newParentSrc );
            }
        }

        for ( LindenNode node : getMovedLeaves() ) {
            modified.add( node.getParent() );

            LindenDirNode newParentSrc = dirTask.getMatch( fileTask.getMatch( node ).getParent());

            if ( newParentSrc != null ) {
                modified.add( newParentSrc );
            }
        }

        for (LindenDirNode node : dirTask.getUnmatchedLeft() ) {
            modified.add( node.getParent() );
        }

        for (LindenNode node : fileTask.getUnmatchedLeft() ) {
            modified.add( node.getParent() );
        }

        for (LindenDirNode node : dirTask.getUnmatchedRight() ) {
            LindenDirNode parentSrc = dirTask.getMatch( node.getParent());

            if ( parentSrc != null ) {
                modified.add( parentSrc );
            }
        }

        for (LindenNode node : fileTask.getUnmatchedRight() ) {
            LindenDirNode parentSrc = dirTask.getMatch( node.getParent());

            if ( parentSrc != null ) {
                modified.add( parentSrc );
            }
        }

        return modified;
    }

    public ChangeVector getChangeVector(LindenNode obj) {

        ChangeVector cv = new ChangeVector();

        LindenNode match = getMatch( obj );

        if ( match == null ) {
            cv.only = true;

            return cv;
        }

        if ( obj.isLeaf() ) {
            cv.content =  fileTask.getDifference( (LindenNode)obj );
        }

        cv.name = ! match.getContent().getName().equals( obj.getContent().getName());

        LindenDirNode parent     = obj.getParent();

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

    public void remove(LindenNode treeNode) {
        if ( treeNode.isLeaf() ) {
            fileTask.remove( (LindenNode)treeNode );
        } else {
            dirTask.remove( (LindenDirNode)treeNode );
        }
    }

    public void merge() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<LindenNode> getComplexModifiedLeaves() {
        Collection<LindenNode> struct = getMovedLeaves();

        struct.addAll( getRenamedLeaves() );

        List<LindenNode> ret = new ArrayList<LindenNode>();

        for ( LindenNode lfm : struct ) {
            if ( fileTask.getMatchQuality( lfm ) < 1.0 ) {
                ret.add( lfm );
            }
        }

        return ret;
    }


    public MatchingTask<LindenDirNode> getDirTask() {
        return dirTask;
    }

    public MatchingTask<LindenNode> getFileTask() {
        return fileTask;
    }

}
