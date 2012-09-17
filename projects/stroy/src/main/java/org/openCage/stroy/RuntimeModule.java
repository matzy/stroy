package org.openCage.stroy;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.openCage.kleinod.immutable.ImmuDate;
import org.openCage.kleinod.io.fspath.FSPathBuilder;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.observe.ObservableRef;
import org.openCage.kleinod.os.OS;
import org.openCage.kleinod.thread.BackgroundExecutor;
import org.openCage.kleinod.thread.BackgroundExecutorImpl;
import org.openCage.lindwurm.content.Content;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.property.ArrayListProperty;
import org.openCage.ruleofthree.property.HashMapProperty;
import org.openCage.ruleofthree.property.ListProperty;
import org.openCage.ruleofthree.property.MapProperty;
import org.openCage.ruleofthree.property.PropStoreImpl;
import org.openCage.ruleofthree.property.PropertyStore;
import org.openCage.ruleofthree.property.PropertyStoreRW;
import org.openCage.ruleofthree.property.SingleFileRW;
import org.openCage.stroy.algo.distance.Distance;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.algo.hash.HashDecider;
import org.openCage.stroy.algo.hash.str.HashDeciderImpl;
import org.openCage.stroy.algo.hash.str.StdStringHash;
import org.openCage.stroy.algo.hash.str.WhitespaceIgnoringHash;
import org.openCage.stroy.app.StroyAppInfo;
import org.openCage.stroy.array.AddIngnorantListMetric;
import org.openCage.stroy.array.ListChangeMetric;
import org.openCage.stroy.array.ReorderIgnorantArrayDistance;
import org.openCage.stroy.file.Action;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.fuzzyHash.file.FuzzyHashGenJ2;
import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.stroy.fuzzyHash.metric.SizeWeightedMetric;
import org.openCage.stroy.graph.matching.LocationCentricMetric;
import org.openCage.stroy.graph.matching.TreeLeafDistanceMetric;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.combined.FastFirstForFiles;
import org.openCage.stroy.text.CLineNoise;
import org.openCage.stroy.text.JavaLineNoiseForDistanceRegex;
import org.openCage.stroy.text.LineNoise;
import org.openCage.stroy.text.LineNoiseDecider;
import org.openCage.stroy.text.LineNoiseDeciderImpl;
import org.openCage.stroy.text.NoNoise;
import org.openCage.stroy.text.SpacesNoise;
import org.openCage.stroy.todo.map.KeyOnlyDistance;
import org.openCage.stroy.ui.DirSelector;
import org.openCage.stroy.ui.DirSelectorImpl;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.difftree.NWayDiffTreeGenImplMessages;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.ui.prefs.PrefsUIImpl;
import org.openCage.stroy.update.UpdateTime;
import org.openCage.stroy.todo.app.AppInfo;
import org.openCage.util.checksum.Checksummer;
import org.openCage.util.checksum.Sha1;
import org.openCage.util.external.DesktopX;
import org.openCage.util.external.DesktopXs;
import org.openCage.util.external.ExecProvider;
import org.openCage.util.external.ExecProviderImpl;
import org.openCage.util.external.OSXDesktopX;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

import static org.openCage.ruleofthree.property.ProviderProvider.getProvider;


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
//        binder.bind( new TypeLiteral<Distance<LindenNode<FileContent>>>(){}).
//                to( new TypeLiteral<TreeNodeFuzzyLeafDistance<FileContent>>(){} );
        binder.bind( new TypeLiteral<Distance<List<String>>>() {})
                .to( new TypeLiteral<ReorderIgnorantArrayDistance<String>>() {});
        binder.bind( new TypeLiteral<Distance<Map<Integer,Boolean>>>() {})
                .to( new TypeLiteral<KeyOnlyDistance<Integer,Boolean>>() {});


        // metrics
        //binder.bind( TreeLeafDistanceMetric.class ).to( ContentBasedMetric.class );
//        binder.bind( TreeLeafDistanceMetric.class ).to( LocationFirstMetric.class );
        binder.bind( TreeLeafDistanceMetric.class ).to( LocationCentricMetric.class );

//        binder.bind( Ignore.class ).to( IgnoreByLists.class );
//        binder.bind( CompareDirs.class ).to( CompareDirsImpl.class );
//        binder.bind( FileMetaPool.class ).to( SourceFileMetaPool.class );
//        binder.bind( FileMetaPool.class ).to( DoubleAllowedFileMetaPool.class );


//        binder.bind( MatchStrategy.class ).annotatedWith( ForIdenticalLeaves.class ).
//                to( IdenticalLeafMatchStrategy.class );
//        binder.bind( new TypeLiteral<MatchStrategy>(){} ).annotatedWith( ForIdenticalLeaves.class ).
//                to( new TypeLiteral<IdenticalLeafMatchStrategy>(){} );
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

//        binder.bind( CompareDirs2.class ).to( CompareDirsImpl2.class );


//        binder.bind( CountChangeMetric.class ).to( CountChangeMetricAddIgnorant.class );
        binder.bind( ListChangeMetric.class ).to( AddIngnorantListMetric.class );
        binder.bind( CountChangeMetric.class ).to( SizeWeightedMetric.class );

        binder.bind( LineNoiseDecider.class ).to( LineNoiseDeciderImpl.class );
        binder.bind(HashDecider.class ).to(HashDeciderImpl.class);
//        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
//                annotatedWith( Names.named("ForJava") /*ForJava.class */).
//                to( FuzzyHashGenJava.class );
//        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
//                annotatedWith( Names.named("ForText") /*ForText.class */).
//                to( FuzzyHashGenText.class );
//        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
//                annotatedWith( Names.named("ForC") /*ForC.class */).
//                to( FuzzyHashGenC.class );
//        binder.bind( new TypeLiteral<FuzzyHashGenerator<File>>() {} ).
//                annotatedWith( Names.named("ForXML") /* ForXML.class */).
//                to( FuzzyHashGenXML.class );

//        binder.bind( DiffProg.class ).to(MacFileMerge.class);

//        binder.bind( ChangeAsColor.class ).to( ChangeAsColorImpl.class);

        binder.bind( DirSelector.class ).to( DirSelectorImpl.class );

        binder.bind( AppInfo.class ).to( StroyAppInfo.class );

        binder.bind( ExecProvider.class ).to(ExecProviderImpl.class );

        binder.bind( new TypeLiteral<NWayDiffPaneGenerator>(){} ).to( new TypeLiteral<NWayDiffTreeGenImplMessages>(){});
        // NWayDiffTreeGenImplMessages.class);

        binder.bind( new TypeLiteral<MatchStrategy>() {} ).annotatedWith(Names.named("FastFirst")).to(FastFirstForFiles.class);

        binder.bind(PrefsUI.class).to( PrefsUIImpl.class ).in(Singleton.class);

        binder.bind( File.class ).annotatedWith( Names.named("PropStoreFile")).toInstance(FSPathBuilder.getPreferences().add("stroy.cphy").toFile());
        binder.bind( PropertyStoreRW.class ).to( SingleFileRW.class );


        binder.bind(BackgroundExecutor.class).to(BackgroundExecutorImpl.class);
        binder.bind( PropertyStore.class ).to( PropStoreImpl.class).in(Singleton.class);

        binder.bind( new TypeLiteral<ObservableRef<String>>() {}).
                annotatedWith( Names.named("dir.first")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<String>>() {},
                        ThreeKey.valueOf("dir.first"),
                        new ObservableRef<String>((""))) );
        binder.bind( new TypeLiteral<ObservableRef<String>>() {}).
                annotatedWith( Names.named("dir.second")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<String>>() {},
                        ThreeKey.valueOf("dir.second"),
                        new ObservableRef<String>((""))) );

        binder.bind( new TypeLiteral<ObservableRef<Locale>>() {}).
                annotatedWith( Names.named("locale")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<Locale>>() {},
                        ThreeKey.valueOf("locale"),
                        new ObservableRef<Locale>((Locale.getDefault()))) );

        binder.bind( new TypeLiteral<ObservableRef<Level>>() {}).
                annotatedWith( Names.named("loglevel")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<Level>>() {},
                        ThreeKey.valueOf("loglevel"),
                        new ObservableRef<Level>(Level.WARNING)) );

        binder.bind( new TypeLiteral<ObservableRef<UpdateTime>>() {}).
                annotatedWith( Names.named("updateTime")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<UpdateTime>>() {},
                        ThreeKey.valueOf("updateTime"),
                        new ObservableRef<UpdateTime>(UpdateTime.weekly)) );

        binder.bind( new TypeLiteral<ObservableRef<ImmuDate>>() {}).
                annotatedWith( Names.named("lastUpdateCheck")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<ImmuDate>>() {},
                        ThreeKey.valueOf("lastUpdateCheck"),
                        new ObservableRef<ImmuDate>( ImmuDate.now())) );

        binder.bind( new TypeLiteral<ObservableRef<String>>() {}).
                annotatedWith( Names.named("DiffProg")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<String>>() {},
                        ThreeKey.valueOf("DiffProg"),
                        new ObservableRef<String>( DesktopXs.OS_STANDARD_TEXT_EDITOR)) );

        binder.bind( new TypeLiteral<ObservableRef<String>>() {}).
                annotatedWith( Names.named("Editor")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<String>>() {},
                        ThreeKey.valueOf("Editor"),
                        new ObservableRef<String>( "")) );

        binder.bind( new TypeLiteral<ObservableRef<String>>() {}).
                annotatedWith( Names.named("progSel")).
                toProvider( getProvider(new TypeLiteral<ObservableRef<String>>() {},
                        ThreeKey.valueOf("progSel"),
                        new ObservableRef<String>( "standard-open")) );


        HashMapProperty<ObservableRef<String>> progList = new HashMapProperty<ObservableRef<String>>();
        progList.put( ThreeKey.valueOf( DesktopXs.STANDARD_OPEN), new ObservableRef<String>(DesktopXs.STANDARD_OPEN));
        progList.put( ThreeKey.valueOf("TextEdit"), new ObservableRef<String>(("/Application/TextEdit.app  ")));
        progList.put( ThreeKey.valueOf("TextWrangler"), new ObservableRef<String>(("/Application/TextWrangler.app")));

        binder.bind( new TypeLiteral<MapProperty<ObservableRef<String>>>() {}).
                annotatedWith( Names.named("progList")).
                toProvider( getProvider(new TypeLiteral<HashMapProperty<ObservableRef<String>>>() {},
                        ThreeKey.valueOf("progList"),
                        progList ));



        binder.bind( new TypeLiteral<MapProperty<Action>>() {} ).toProvider(
             getProvider( new TypeLiteral<HashMapProperty<Action>>() {},
                          ThreeKey.valueOf("fileTypes"),
                          FileTypes.getInitialMap() ));


        binder.bind( new TypeLiteral<ListProperty<String>>(){}).annotatedWith(Names.named("ignores")).toProvider(
                getProvider(new TypeLiteral<ArrayListProperty<String>>() {
                },
                        ThreeKey.valueOf("ignores"),
                        IgnoreCentral.getInitial()));

        if (OS.isOSX() ) {
            binder.bind(DesktopX.class).to(OSXDesktopX.class);
        }

        binder.bind(Checksummer.class).to(Sha1.class);
        binder.bind( new TypeLiteral<F1<FuzzyHash,Content>>(){}).to( FuzzyHashGenJ2.class );
    }

}
