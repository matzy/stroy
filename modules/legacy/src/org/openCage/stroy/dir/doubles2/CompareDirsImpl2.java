package org.openCage.stroy.dir.doubles2;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Guice;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.combined.FastFirst;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.RuntimeModule;
import org.openCage.util.logging.Log;
import org.openCage.util.iterator.T2;

import java.io.File;                                                                                       

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

public class CompareDirsImpl2 implements CompareDirs2 {

    private final FileTreeMatchingTaskBuilder           builder;
//    private final TreeMatchingTaskStrategy<FileContent> strategy;

    private final MatchStrategy<FileContent> matchStrategy;

    @Inject
    public CompareDirsImpl2( final FileTreeMatchingTaskBuilder builder ) { //,
//                             final TreeMatchingTaskStrategy<FileContent>    strategy ) {

        this.builder  = builder;
//        this.strategy = strategy;

        Injector injector = Guice.createInjector( new RuntimeModule() );
        matchStrategy = injector.getInstance( FastFirst.class );
//        matchStrategy = injector.getInstance( StructureOnly.class );
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
