package org.openCage.stroy.graph.node;

import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.SimpleTreeNode;
import org.openCage.lindwurm.content.ReducedContent;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.RuntimeModule;

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

    public LindenNode d( ReducedContent t, LindenNode... childs ) {
        return new SimpleTreeNode( t, childs );
    }

    public LindenNode d( String name, LindenNode... childs ) {
        return new SimpleTreeNode( new ReducedContent( name, "dir"), childs );
    }

    public LindenNode l( ReducedContent t ) {
        return new SimpleTreeNode( t );
    }

    public LindenNode l( String name ) {
        Set<Integer> set = new HashSet<Integer>();
        set.add( new Integer( name.hashCode() ));
        return new SimpleTreeNode( new ReducedContent( name )); //, "" + name.hashCode(), hashSetFactory.create(set), "string") );
    }

    public LindenNode l( String name, String checksum ) {
        Set<Integer> set = new HashSet<Integer>();
        set.add( new Integer( name.hashCode() ));
        return new SimpleTreeNode( new ReducedContent( name )); //, checksum, hashSetFactory.create(set), "string") );
    }
}
