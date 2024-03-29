package org.openCage.stroy;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

import com.google.inject.name.Names;
import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.ApplicationWiring;
import org.openCage.stroy.array.AddIngnorantListMetric;
import org.openCage.stroy.array.ListChangeMetric;
import org.openCage.stroy.array.ReorderIgnorantArrayDistance;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilderImpl;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.filter.IgnoreByLists;
import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.metric.SizeWeightedMetric;
import org.openCage.stroy.fuzzyHash.file.*;
import org.openCage.stroy.graph.matching.LocationCentricMetric;
import org.openCage.stroy.graph.matching.TreeLeafDistanceMetric;
import org.openCage.stroy.map.KeyOnlyDistance;
import org.openCage.stroy.text.*;
import org.openCage.stroy.ui.*;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.difftree.NWayDiffTreeGenImplMessages;
import org.openCage.stroy.app.ApplicationProvider;
import org.openCage.stroy.protocol.Distance;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.algo.hash.str.StdStringHash;
import org.openCage.stroy.algo.hash.str.WhitespaceIgnoringHash;
import org.openCage.util.external.ExecProvider;
import org.openCage.util.external.ExecProviderImpl;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.openCage.ui.wiring.UIWiring;

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

public class RuntimeModule implements Module {


    public void configure(Binder binder) {

    	binder.install( new ApplicationWiring());
        binder.install( new UIWiring());
    	
    	binder.bind( Application.class ).toProvider( ApplicationProvider.class );

    	
        //
        // LineNoise
        //
        binder.bind( LineNoise.class ).annotatedWith( Names.named( "java" ) ).to( JavaLineNoiseForDistanceRegex.class );
        binder.bind( LineNoise.class ).annotatedWith( Names.named( "text") ).to( NoNoise.class );
        binder.bind( LineNoise.class ).annotatedWith( Names.named( "c") ).to( CLineNoise.class );
        binder.bind( LineNoise.class ).annotatedWith( Names.named( "xml") ).to( SpacesNoise.class );


        //
        // Text Line Hashes
        //
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named( "java" )).
                to( WhitespaceIgnoringHash.class );
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named( "text") ).
                to( StdStringHash.class );
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named( "c") ).
                to( WhitespaceIgnoringHash.class );
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named( "xml") ).
                to( WhitespaceIgnoringHash.class );

        //
        // Distance
        //
//        binder.bind( new TypeLiteral<Distance<TreeNode<FileContent>>>(){}).
//                to( new TypeLiteral<TreeNodeFuzzyLeafDistance<FileContent>>(){} );
        binder.bind( new TypeLiteral<Distance<List<String>>>() {})
                .to( new TypeLiteral<ReorderIgnorantArrayDistance<String>>() {});
        binder.bind( new TypeLiteral<Distance<Map<Integer,Boolean>>>() {})
                .to( new TypeLiteral<KeyOnlyDistance<Integer,Boolean>>() {});


        // metrics
        //binder.bind( TreeLeafDistanceMetric.class ).to( ContentBasedMetric.class );
//        binder.bind( TreeLeafDistanceMetric.class ).to( LocationFirstMetric.class );
        binder.bind( TreeLeafDistanceMetric.class ).to( LocationCentricMetric.class );

        binder.bind( Ignore.class ).to( IgnoreByLists.class );
//        binder.bind( CompareDirs.class ).to( CompareDirsImpl.class );
//        binder.bind( FileMetaPool.class ).to( SourceFileMetaPool.class );
//        binder.bind( FileMetaPool.class ).to( DoubleAllowedFileMetaPool.class );


//        binder.bind( MatchStrategy.class ).annotatedWith( ForIdenticalLeaves.class ).
//                to( IdenticalLeafMatchStrategy.class );
//        binder.bind( new TypeLiteral<MatchStrategy<Content>>(){} ).annotatedWith( ForIdenticalLeaves.class ).
//                to( new TypeLiteral<IdenticalLeafMatchStrategy<Content>>(){} );
//        binder.bind( new TypeLiteral<MatchStrategy<FileContent>>(){} ).annotatedWith( ForIdenticalLeaves.class ).
//                to( new TypeLiteral<IdenticalLeafMatchStrategy<FileContent>>(){} );
//        binder.bind( new TypeLiteral<MatchStrategy<FileContent>>(){} ).annotatedWith( ForIdenticalLeaves.class ).
//                to( new TypeLiteral<IdenticalLeafMatchStrategy<FileContent>>(){} );

//        binder.bind( new TypeLiteral<MatchStrategy<FileContent>>(){} ).annotatedWith( ForDirHierarchies.class ).
//                to( new TypeLiteral<HierarchicalDirMatching<FileContent>>(){} );
//        binder.bind( new TypeLiteral<MatchStrategy<FileContent>>(){} ).annotatedWith( ForDuplicates.class ).
//                to( new TypeLiteral<DuplicateMatching<FileContent>>(){} );
//        binder.bind( new TypeLiteral<MatchStrategy<FileContent>>(){} ).annotatedWith( ForHistoryMatches.class ).
//                to( new TypeLiteral<HistoricalMatching<FileContent>>(){} );
//        binder.bind( new TypeLiteral<MatchStrategy<FileContent>>(){} ).annotatedWith( ForSimpleDirMatching.class ).
//                to( new TypeLiteral<SimpleDirMatching<FileContent>>(){} );
//
////        binder.bind( new TypeLiteral<TreeMatchingTaskStrategy<FileContent>>(){} ).
////                to( new TypeLiteral<EasyFirstStrategy<FileContent>>(){});
//        binder.bind( new TypeLiteral<TreeMatchingTaskStrategy<FileContent>>(){} ).
//                to( EasyFirstStrategyFC.class );
        binder.bind( FileTreeMatchingTaskBuilder.class ).to( FileTreeMatchingTaskBuilderImpl.class );

//        binder.bind( CompareDirs2.class ).to( CompareDirsImpl2.class );


//        binder.bind( CountChangeMetric.class ).to( CountChangeMetricAddIgnorant.class );
        binder.bind( ListChangeMetric.class ).to( AddIngnorantListMetric.class );
        binder.bind( CountChangeMetric.class ).to( SizeWeightedMetric.class );

        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).to( FuzzyHashGeneratorForFiles.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named( "java" ) ).
                to( FuzzyHashGenJava.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named( "text" ) ).
                to( FuzzyHashGenText.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named( "c" ) ).
                to( FuzzyHashGenC.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named( "xml" ) ).
                to( FuzzyHashGenXML.class );
        
//        binder.bind( DiffProg.class ).to(MacFileMerge.class);

//        binder.bind( ChangeAsColor.class ).to( ChangeAsColorImpl.class);

        binder.bind( DirSelector.class ).to( DirSelectorImpl.class );

        binder.bind( ExecProvider.class ).to(ExecProviderImpl.class );

        binder.bind(NWayDiffPaneGenerator.class ).to( NWayDiffTreeGenImplMessages.class );

    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RuntimeModule;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
