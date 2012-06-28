package org.openCage.stroy.algo.tree;

import org.openCage.stroy.algo.tree.Fiel;

import java.util.List;

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

/**
 * A Node class to build acyclic directed graphs = trees
 * Noed is a deliberate misspelling of Node
 */
public interface Noed {

    /**
     * Return whether this noed is a leaf
     * Note: A directory without children is not a leaf
     * @return true iff object is a leaf
     */
    public boolean        isLeaf();

    public boolean        isReadOnly();


    public String         getName();

    /**
     * Set the parent, i.e. add this noed to a tree
     * this should only be called by addChild
     * @param parent
     */
    public void           setParent( Noed parent );

    /**
     * Gets the parent noed
     * @return the parent
     */
    public Noed           getParent();

    /**
     * return the list of childs (readonly)
     * @return the children of the Noed
     */
    public List<Noed>     getChildren();

    /**
     * Add a child (calls addParent on child)
     * @param noed
     */
    public void           addChild( Noed noed );

    /**
     * Gets the attached field
     * @return the field
     */
    public Fiel getFiel();
}
