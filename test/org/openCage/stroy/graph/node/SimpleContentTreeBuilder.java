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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
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
