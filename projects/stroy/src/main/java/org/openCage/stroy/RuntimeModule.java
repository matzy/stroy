package org.openCage.stroy;

import com.google.inject.*;
import com.google.inject.name.Names;
import org.openCage.comphy.*;
import org.openCage.comphy.property.ImmuProp;
import org.openCage.comphy.property.ListProperty;
import org.openCage.comphy.property.MapProperty;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.lang.OS;
import org.openCage.lang.inc.Str;
import org.openCage.lang.inc.Strng;
import org.openCage.stroy.algo.hash.HashDecider;
import org.openCage.stroy.algo.hash.str.HashDeciderImpl;
import org.openCage.stroy.array.AddIngnorantListMetric;
import org.openCage.stroy.array.ListChangeMetric;
import org.openCage.stroy.array.ReorderIgnorantArrayDistance;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilderImpl;
import org.openCage.stroy.file.Action;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.filter.IgnoreByLists;
import org.openCage.stroy.filter.IgnoreCentral;
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
import org.openCage.stroy.update.UpdateSelectionProperty;
import org.openCage.util.app.*;
import org.openCage.util.external.*;
import org.openCage.util.prefs.DateProperty;
import org.openCage.util.prefs.LocaleSelectionProperty;
import org.openCage.util.prefs.LogLevelSelectionProperty5;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.openCage.lang.inc.Strng.S;

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

        binder.bind( new TypeLiteral<FuzzyHashGenerator<Content>>() {} ).to( FuzzyHashGenJ2.class );
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

        binder.bind( new TypeLiteral<NWayDiffPaneGenerator<Content>>(){} ).to( new TypeLiteral<NWayDiffTreeGenImplMessages<Content>>(){});
        // NWayDiffTreeGenImplMessages.class);

        binder.bind( new TypeLiteral<MatchStrategy<? extends Content>>() {} ).annotatedWith(Names.named("FastFirst")).to(FastFirstForFiles.class);

        binder.bind(PrefsUI.class).to( PrefsUIImpl.class ).in(Singleton.class);

        binder.bind( File.class ).annotatedWith( Names.named("PropStoreFile")).toInstance(FSPathBuilder.getPreferences().add("stroy.cphy").toFile());

        binder.bind(BackgroundExecutor.class).to(BackgroundExecutorImpl.class);
        binder.bind( PropertyStore.class ).to( PersistantPropertyStore.class).in( Singleton.class );

        binder.bind( new TypeLiteral<ImmuProp<Str>>() {}).
                annotatedWith( Names.named("dir.first")).
                toProvider( new PropertyProvider9<ImmuProp<Str>>() {
                    @Override
                    public TypeLiteral getTypeLiteral() {
                        return new TypeLiteral<ImmuProp<Str>>() {};
                    }

                    @Override
                    protected Str getKey() {
                        return S("dir.first");
                    }

                    @Override
                    protected ImmuProp<Str> getDefault() {
                        return new ImmuProp<Str>(S(""));
                    }
                });
        binder.bind( new TypeLiteral<ImmuProp<Str>>() {}).
                annotatedWith( Names.named("dir.second")).
                toProvider( new PropertyProvider7<ImmuProp<Str>>() {
                    @Override
                    public TypeLiteral<ImmuProp<Str>> getTypeLiteral() {
                        return (TypeLiteral)new TypeLiteral<ImmuProp<Strng>>() {};
                    }

                    @Override
                    protected Str getKey() {
                        return S("dir.second");
                    }

                    @Override
                    protected ImmuProp<Str> getDefault() {
                        return (ImmuProp)new ImmuProp<Strng>(S(""));
                    }
                });

//        binder.bind( StringProperty.class ).
//                annotatedWith( Names.named("dir.first")).
//                toProvider( new StringPropertyProvider5( S("dir.first"), ""));

//        binder.bind( StringProperty.class ).annotatedWith(Names.named("dir.second")).
//                toProvider( new StringPropertyProvider5( S("dir.second"), ""));


        binder.bind(LocaleSelectionProperty.class ).toProvider(new PropertyProvider5<LocaleSelectionProperty>() {
            @Override
            public Class<? extends LocaleSelectionProperty> getRealClass() {
                return LocaleSelectionProperty.class;
            }

            @Override
            protected Str getKey() {
                return S("locale");
            }

            @Override
            protected LocaleSelectionProperty getDefault() {
                return new LocaleSelectionProperty();
            }
        });

        binder.bind(LogLevelSelectionProperty5.class).toProvider( new PropertyProvider5<LogLevelSelectionProperty5>() {
            @Override
            public Class<? extends LogLevelSelectionProperty5> getRealClass() {
                return LogLevelSelectionProperty5.class;
            }

            @Override
            protected Str getKey() {
                return S("loglevel");
            }

            @Override
            protected LogLevelSelectionProperty5 getDefault() {
                return new LogLevelSelectionProperty5();
            }
        });

        binder.bind(UpdateSelectionProperty.class).toProvider( new PropertyProvider5<UpdateSelectionProperty>() {
            @Override
            public Class<? extends UpdateSelectionProperty> getRealClass() {
                return UpdateSelectionProperty.class;
            }

            @Override
            protected Str getKey() {
                return S("update");
            }

            @Override
            protected UpdateSelectionProperty getDefault() {
                return new UpdateSelectionProperty();
            }
        });

        binder.bind( DateProperty.class ).annotatedWith(Names.named("lastUpdateCheck")).
                toProvider( new PropertyProvider5<DateProperty>(){
                    @Override
                    public Class<? extends DateProperty> getRealClass() {
                        return DateProperty.class;
                    }

                    @Override
                    protected Str getKey() {
                        return S("lastUpdateCheck");
                    }

                    @Override
                    protected DateProperty getDefault() {
                        return DateProperty.now();
                    }
                } );

        binder.bind( new TypeLiteral<ImmuProp<Str>>() {}).
                annotatedWith(Names.named("Editor")).
                toProvider(new PropertyProvider9<ImmuProp<Str>>() {
                    @Override
                    public TypeLiteral<ImmuProp<Str>> getTypeLiteral() {
                        return (TypeLiteral)new TypeLiteral<ImmuProp<Strng>>() {};
                    }

                    @Override
                    protected Str getKey() {
                        return S("Editor");
                    }

                    @Override
                    protected ImmuProp<Str> getDefault() {
                        return new ImmuProp<Str>(DesktopXs.OS_STANDARD_TEXT_EDITOR);
                    }
                });
        binder.bind( new TypeLiteral<ImmuProp<Str>>() {}).
                annotatedWith( Names.named("DiffProg")).
                toProvider( new PropertyProvider9<ImmuProp<Str>>() {
                    @Override
                    public TypeLiteral<ImmuProp<Str>> getTypeLiteral() {
                        return (TypeLiteral)new TypeLiteral<ImmuProp<Strng>>() {};
                    }

                    @Override
                    protected Str getKey() {
                        return S("DiffProg");
                    }

                    @Override
                    protected ImmuProp<Str> getDefault() {
                        return (ImmuProp)new ImmuProp<Strng>(S(""));
                    }
                });

        binder.bind( new TypeLiteral<ImmuProp<Str>>() {}).
                annotatedWith( Names.named("progSel")).
                toProvider( new PropertyProvider9<ImmuProp<Str>>() {
                    @Override
                    public TypeLiteral<ImmuProp<Str>> getTypeLiteral() {
                        return (TypeLiteral)new TypeLiteral<ImmuProp<Strng>>() {};
                    }

                    @Override
                    protected Str getKey() {
                        return S("progSel");
                    }

                    @Override
                    protected ImmuProp<Str> getDefault() {
                        return (ImmuProp)new ImmuProp<Strng>(S("standard-open"));
                    }
                });

        binder.bind( new TypeLiteral<MapProperty<ImmuProp<Str>>>() {}).
                annotatedWith(Names.named("progList")).
                toProvider( new PropertyProvider9<MapProperty<ImmuProp<Str>>>() {
            @Override
            public TypeLiteral getTypeLiteral() {
                return
                        (TypeLiteral)new TypeLiteral<MapProperty<ImmuProp<Strng>>>() {};
            }

            @Override
            protected Str getKey() {
                return S("progList");

            }

            @Override
            protected MapProperty<ImmuProp<Str>> getDefault() {
                MapProperty<ImmuProp<Str>> ret = new MapProperty<ImmuProp<Str>>();
                ret.put( DesktopXs.STANDARD_OPEN, new ImmuProp<Str>(DesktopXs.STANDARD_OPEN));
                ret.put( S("TextEdit"), new ImmuProp<Str>(S("/Application/TextEdit.app  ")));
                ret.put( S("TextWrangler"), new ImmuProp<Str>(S("/Application/TextWrangler.app")));
                return ret;
            }
        });
//        binder.bind(StringProperty.class).annotatedWith(Names.named("Editor")).toProvider(new StringPropertyProvider5( S("Editor"), "TextEdit"));
//        binder.bind(StringProperty.class).annotatedWith(Names.named("DiffProg")).toProvider(new StringPropertyProvider5( S("DiffProg"), "FileMerge"));



        binder.bind( new TypeLiteral<MapProperty<Action>>() {} ).toProvider( new PropertyProvider9<MapProperty<Action>>() {
            @Override
            public TypeLiteral<MapProperty<Action>> getTypeLiteral() {
                return new TypeLiteral<MapProperty<Action>>() {};
            }

            @Override
            protected Str getKey() {
                return S("fileTypes");
            }

            @Override
            protected MapProperty<Action> getDefault() {
                return FileTypes.getInitialMap();
            }
        });

        binder.bind( new TypeLiteral<ListProperty<StringProperty>>(){}).annotatedWith(Names.named("ignores")).toProvider(
                new PropertyProvider9<ListProperty<StringProperty>>() {
                    @Override
                    public TypeLiteral<ListProperty<StringProperty>> getTypeLiteral() {
                        return new TypeLiteral<ListProperty<StringProperty>>() {};
                    }

                    @Override
                    protected Str getKey() {
                        return S("ignores");
                    }

                    @Override
                    protected ListProperty<StringProperty> getDefault() {
                        return IgnoreCentral.getInitial();
                    }
                });

        if (OS.isOSX() ) {
            binder.bind(DesktopX.class).to(OSXDesktopX.class);
        }
    }

}
