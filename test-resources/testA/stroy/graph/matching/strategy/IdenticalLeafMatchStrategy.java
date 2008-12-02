package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.util.logging.Log;

import java.util.HashMap;
import java.util.Map;

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

public class IdenticalLeafMatchStrategy<T extends Content> implements MatchStrategy<T> {

    public void match(TreeMatchingTask<T> treeMatchingTask) {

        Log.fine( "match Identical" );

        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }
        

        Map<String, SameContent<T>> sames = computeHashes(treeMatchingTask);
        matchSames(treeMatchingTask, sames );

    }

    private void matchSames( TreeMatchingTask<T> treeMatchingTask, Map<String, SameContent<T>> sames) {

        for ( SameContent<T> sh : sames.values() ) {

            if ( !sh.isSingle() ) {
               treeMatchingTask.addDup( sh );
            } else if ( sh.isOk() ) {
                treeMatchingTask.getLeaves().match( sh.getSingleSrc(), sh.getSingleTgt(), 1.0 );
            } else if ( sh.isSingleNew() ) {
            } else if ( sh.isSingleOld() )  {
            } else {
                throw new Error( "huh" );
            }
        }
    }


    private Map<String, SameContent<T>> computeHashes( TreeMatchingTask<T> matchingTask) {
        Map<String, SameContent<T>> sames = new HashMap<String, SameContent<T>>();

        for ( TreeLeafNode<T> lfm : matchingTask.getLeaves().getUnmatchedLeft() ) {
            String checksum = lfm.getContent().getChecksum();

            SameContent<T> sh;

            if ( sames.containsKey( checksum )) {
                sh = sames.get( checksum );
            } else {
                sh = new SameContent<T>();
                sames.put( checksum, sh );
            }

            sh.add( lfm, true );
        }

        for ( TreeLeafNode<T> lfm : matchingTask.getLeaves().getUnmatchedRight() ) {
            String checksum = lfm.getContent().getChecksum();

            SameContent<T> sh;

            if ( sames.containsKey( checksum )) {
                sh = sames.get( checksum );
            } else {
                sh = new SameContent<T>();
                sames.put( checksum, sh );
            }

            sh.add( lfm, false );
        }

        return sames;
    }


}
