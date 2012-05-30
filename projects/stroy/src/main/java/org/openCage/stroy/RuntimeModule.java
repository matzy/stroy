package org.openCage.stroy;

import com.google.inject.*;
import com.google.inject.name.Names;
import org.openCage.comphy.*;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.stroy.array.AddIngnorantListMetric;
import org.openCage.stroy.array.ListChangeMetric;
import org.openCage.stroy.array.ReorderIgnorantArrayDistance;
import org.openCage.stroy.dir.FileContent;
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
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.combined.FastFirstForFiles;
import org.openCage.stroy.map.KeyOnlyDistance;
import org.openCage.stroy.text.*;
import org.openCage.stroy.ui.*;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.difftree.NWayDiffTreeGenImplMessages;
import org.openCage.stroy.app.StroyAppInfo;
import org.openCage.stroy.algo.distance.Distance;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.algo.hash.str.StdStringHash;
import org.openCage.stroy.algo.hash.str.WhitespaceIgnoringHash;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.ui.prefs.PrefsUIImpl;
import org.openCage.util.app.*;
import org.openCage.util.external.ExecProvider;
import org.openCage.util.external.ExecProviderImpl;

import java.io.File;
import java.util.List;
import java.util.Map;

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

        //
        // LineNoise
        //
        binder.bind( LineNoise.class ).annotatedWith( Names.named("ForJava") ).to( JavaLineNoiseForDistanceRegex.class );
        binder.bind( LineNoise.class ).annotatedWith( Names.named("ForText") ).to( NoNoise.class );
        binder.bind( LineNoise.class ).annotatedWith( Names.named("ForC") ).to( CLineNoise.class );
        binder.bind( LineNoise.class ).annotatedWith( Names.named("ForXML") ).to( SpacesNoise.class );


        //
        // Text Line Hashes
        //
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named("ForJava") ).
                to( WhitespaceIgnoringHash.class );
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named("ForText") ).
                to( StdStringHash.class );
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named("ForC") ).
                to( WhitespaceIgnoringHash.class );
        binder.bind( new TypeLiteral<Hash<String>>() {} ).
                annotatedWith( Names.named("ForXML") ).
                to( WhitespaceIgnoringHash.class );

        //
        // Distance
        //
//        binder.bind( new TypeLiteral<Distance<TreeLeafNode<FileContent>>>(){}).
//                to( new TypeLiteral<TreeLeafNodeFuzzyLeafDistance<FileContent>>(){} );
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
                annotatedWith( Names.named("ForJava") /*ForJava.class */).
                to( FuzzyHashGenJava.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named("ForText") /*ForText.class */).
                to( FuzzyHashGenText.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named("ForC") /*ForC.class */).
                to( FuzzyHashGenC.class );
        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
                annotatedWith( Names.named("ForXML") /* ForXML.class */).
                to( FuzzyHashGenXML.class );

//        binder.bind( DiffProg.class ).to(MacFileMerge.class);

//        binder.bind( ChangeAsColor.class ).to( ChangeAsColorImpl.class);

        binder.bind( DirSelector.class ).to( DirSelectorImpl.class );

        binder.bind( AppInfo.class ).to( StroyAppInfo.class );

        binder.bind( ExecProvider.class ).to(ExecProviderImpl.class );

        binder.bind( new TypeLiteral<NWayDiffPaneGenerator<FileContent>>(){} ).to( new TypeLiteral<NWayDiffTreeGenImplMessages<FileContent>>(){});
        // NWayDiffTreeGenImplMessages.class);

        binder.bind( new TypeLiteral<MatchStrategy<? extends FileContent>>() {} ).annotatedWith(Names.named("FastFirst")).to(FastFirstForFiles.class);

        binder.bind(PrefsUI.class).to( PrefsUIImpl.class ).in(Singleton.class);

        binder.bind( File.class ).annotatedWith( Names.named("PropStoreFile")).toInstance(FSPathBuilder.getPreferences().add("stroy.cphy").toFile());

        binder.bind( BackgroundExecutor.class ).to(BackgroundExecutorImpl.class);
        binder.bind( PropertyStore.class ).to( PersistantPropertyStore.class);
        binder.bind( StringProperty.class ).annotatedWith( Names.named("dir.first")).toProvider( DirFirstProv.class );

    }

    public static class DirFirstProv extends StringPropertyProvider {
        @Inject
        public DirFirstProv( PropertyStore store) {
            super(store, new Key("dir.first"), "" );
        }
    }

}
