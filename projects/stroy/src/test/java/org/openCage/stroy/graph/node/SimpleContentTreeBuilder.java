package org.openCage.stroy.graph.node;

import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.fuzzyHash.FuzzyHashNever;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.stroy.graph.matching.strategy.HistoricalMatching;

import java.util.Collections;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import com.google.inject.Injector;
import com.google.inject.Guice;

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
public class SimpleContentTreeBuilder {

    private FuzzyHashSetFactory hashSetFactory;

    public SimpleContentTreeBuilder() {
        Injector injector = Guice.createInjector( new RuntimeModule() );
        hashSetFactory = injector.getInstance( FuzzyHashSetFactory.class );
    }

    public TreeNode<ReducedContent> d( ReducedContent t, TreeNode<ReducedContent> ... childs ) {
        return new SimpleTreeNode<ReducedContent>( t, childs );
    }

    public TreeNode<ReducedContent> d( String name, TreeNode<ReducedContent> ... childs ) {
        return new SimpleTreeNode<ReducedContent>( new ReducedContent( name, "" + name.hashCode(), hashSetFactory.create( Collections.EMPTY_SET), "dir"), childs );
    }

    public TreeNode<ReducedContent> l( ReducedContent t ) {
        return new SimpleTreeNode<ReducedContent>( t );
    }

    public TreeNode<ReducedContent> l( String name ) {
        Set<Integer> set = new HashSet<Integer>();
        set.add( new Integer( name.hashCode() ));
        return new SimpleTreeNode<ReducedContent>( new ReducedContent( name, "" + name.hashCode(), hashSetFactory.create(set), "string") );
    }

    public TreeNode<ReducedContent> l( String name, String checksum ) {
        Set<Integer> set = new HashSet<Integer>();
        set.add( new Integer( name.hashCode() ));        
        return new SimpleTreeNode<ReducedContent>( new ReducedContent( name, checksum, hashSetFactory.create(set), "string") );
    }
}
