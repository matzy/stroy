//package org.openCage.stroy.dir.doubles2;
//
//import com.google.inject.Inject;
//import org.openCage.stroy.dir.FileContent;
//import org.openCage.stroy.graph.matching.TreeMatchingTask;
//import org.openCage.stroy.task.*;
//import org.openCage.stroy.content.Content;
//import org.openCage.util.state.StateImpl;
//
///**
// * stroy, a differencing tool
// * Copyright (C) Aug 25, 2007 Stephan Pfab
// * <p/>
// * This library is free software; you can redistribute it and/or
// * modify it under the terms of the GNU Lesser General Public
// * License as published by the Free Software Foundation; either
// * version 2.1 of the License, or (at your option) any later version.
// * <p/>
// * This library is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// * Lesser General Public License for more details.
// * <p/>
// * You should have received a copy of the GNU Lesser General Public
// * License along with this library; if not, write to the Free Software
// * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
// */
//public class EasyFirstStrategy<T extends Content> implements TreeMatchingTaskStrategy<T> {
//
//    private final MatchStrategy<T> identicalLeafMatcher;
//    private final MatchStrategy<T> hirDirMatcher;
//    private final MatchStrategy<T> dupMatcher;
//    private final MatchStrategy<T> historyMatcher;
//    private final MatchStrategy<T> simpleDirMatcher;
//
//    @Inject
//    public EasyFirstStrategy(
//            @ForIdenticalLeaves   final MatchStrategy<T>  identicalLeafMatcher,
//            @ForSimpleDirMatching final MatchStrategy<T>  simpleDirMatcher,
//            @ForDirHierarchies    final MatchStrategy<T>  hirDirMatcher,
//            @ForDuplicates        final MatchStrategy<T>  dupMatcher,
//            @ForHistoryMatches    final MatchStrategy<T>  historyMatcher ) {
//        this.identicalLeafMatcher = identicalLeafMatcher;
//        this.hirDirMatcher        = hirDirMatcher;
//        this.dupMatcher           = dupMatcher;
//        this.historyMatcher       = historyMatcher;
//        this.simpleDirMatcher     = simpleDirMatcher;
//    }
//
//    public void applyStrategy( final TreeMatchingTask<T> task) {
//
//        task.shortStatus();
//
//        new StateImpl().setState( "matching identical leafs" );
//        identicalLeafMatcher.match(task);
//        task.shortStatus();
//
//        new StateImpl().setState( "Simple dir matching" );
//        simpleDirMatcher.match(task);
//        task.shortStatus();
//
//        new StateImpl().setState( "dupplicates" );
//        dupMatcher.match(task);
//        task.shortStatus();
//
//        new StateImpl().setState( "hierarchy dir" );
//        hirDirMatcher.match(task);
//        task.shortStatus();
//
//        new StateImpl().setState( "history" );
//        historyMatcher.match(task);
//        task.shortStatus();
//
//        new StateImpl().setState( "hierarchy dir" );
//        hirDirMatcher.match(task);
//        task.shortStatus();
//
//        new StateImpl().setState( "" );
//        //To change body of implemented methods use File | Settings | File Templates.
//
//        // TODO
//        //match radically changed files with same name and parent
//
//        //treeMatchingTask.state();
//
//    }
//}
