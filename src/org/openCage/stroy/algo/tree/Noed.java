package org.openCage.stroy.algo.tree;

import org.openCage.stroy.algo.tree.Fiel;

import java.util.List;

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
