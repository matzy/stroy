package org.openCage.stroy.dir.doubles2;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.structure.T2;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.util.logging.Log;

import java.io.File;                                                                                       

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

public class CompareDirsImpl2 implements CompareDirs2 {

    private final FileTreeMatchingTaskBuilder           builder;
//    private final TreeMatchingTaskStrategy<FileContent> strategy;

    private final MatchStrategy<FileContent> matchStrategy;

    @Inject
    public CompareDirsImpl2( final @Named("FastFirst") MatchStrategy<FileContent> matchStrategy, final FileTreeMatchingTaskBuilder builder ) { //,
//                             final TreeMatchingTaskStrategy<FileContent>    strategy ) {

        this.builder  = builder;
        this.matchStrategy = matchStrategy;
    }

    public TreeMatchingTask<FileContent> compare( Ignore ignore, File one, File two) {

        Log.info( "reading both dirs" );
        TreeMatchingTask<FileContent> treeMatchingTask = builder.build( ignore, one, two );

//        strategy.applyStrategy( treeMatchingTask );
//        matchStrategy.match( treeMatchingTask, new Reporter(){
//            public void detail(String str) {
//                // TODO impl or kill
//            }
//        } );

        return treeMatchingTask;
    }


    public T2<TreeMatchingTask<FileContent>, TreeMatchingTask<FileContent>> compare(Ignore ignore, File one, File two, File three) {

        Log.info( "reading dir 1 and 2" );

        TreeMatchingTask<FileContent> treeMatchingGraph1 = builder.build( ignore, one, two );

        Log.info(  "reading dir 3" );
        TreeMatchingTask<FileContent> treeMatchingGraph2 = builder.build( ignore, treeMatchingGraph1, three );


//        strategy.applyStrategy( treeMatchingGraph1 );
//        strategy.applyStrategy( treeMatchingGraph2 );
//        matchStrategy.match( treeMatchingGraph1, new Reporter() {
//            public void detail(String str) {
//            }
//        });
//        matchStrategy.match( treeMatchingGraph2, new Reporter(){
//            public void detail(String str) {
//            }
//        } );


        return new T2<TreeMatchingTask<FileContent>, TreeMatchingTask<FileContent>>(treeMatchingGraph1,treeMatchingGraph2);
    }
}
