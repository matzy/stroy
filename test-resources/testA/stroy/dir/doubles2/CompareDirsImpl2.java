package org.openCage.stroy.dir.doubles2;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Guice;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.combined.StandardFirst;
import org.openCage.stroy.graph.matching.strategy.combined.StructureOnly;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.RuntimeModule;
import org.openCage.util.logging.Log;
import org.openCage.util.iterator.T2;

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
        matchStrategy = injector.getInstance( StandardFirst.class );
//        matchStrategy = injector.getInstance( StructureOnly.class );
    }

    public TreeMatchingTask<FileContent> compare( Ignore ignore, File one, File two) {

        Log.info( "reading both dirs" );
        TreeMatchingTask<FileContent> treeMatchingTask = builder.build( ignore, one, two );

//        strategy.applyStrategy( treeMatchingTask );
        matchStrategy.match( treeMatchingTask );

        return treeMatchingTask;
    }


    public T2<TreeMatchingTask<FileContent>, TreeMatchingTask<FileContent>> compare(Ignore ignore, File one, File two, File three) {

        Log.info( "reading dir 1 and 2" );

        TreeMatchingTask<FileContent> treeMatchingGraph1 = builder.build( ignore, one, two );

        Log.info(  "reading dir 3" );
        TreeMatchingTask<FileContent> treeMatchingGraph2 = builder.build( ignore, treeMatchingGraph1, three );


//        strategy.applyStrategy( treeMatchingGraph1 );
//        strategy.applyStrategy( treeMatchingGraph2 );
        matchStrategy.match( treeMatchingGraph1 );
        matchStrategy.match( treeMatchingGraph2 );


        return new T2<TreeMatchingTask<FileContent>, TreeMatchingTask<FileContent>>(treeMatchingGraph1,treeMatchingGraph2);
    }
}
