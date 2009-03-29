package org.openCage.utils.iterator;


import org.openCage.lang.F1;

import java.util.Iterator;
import java.util.Collection;

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

public class DepthFirstIterator<T> implements Iterator<T> {

    private T node;
    private F1<Boolean,T> isLeaf;
    private F1<Collection<T>,T> getChildren;
    private F1<T,T> getParent;

    public DepthFirstIterator( T root,
                               F1<Boolean,T> isLeaf,
                               F1<Collection<T>,T> getChildren,
                               F1<T,T> getParent ) {
        this.node = root;
        this.isLeaf = isLeaf;
        this.getChildren = getChildren;
        this.getParent = getParent;

    }

    public boolean hasNext() {
        return node != null;
    }

    public T next() {
        T oldNode = node;

        if ( !isLeaf.c(node) ) {
            Collection<T> childs = getChildren.c( node );

            if ( childs != null && childs.size() > 0 ) {
                node = childs.iterator().next();
                return oldNode;
            }
        }


        T parent = getParent.c(node);

        while ( parent != null ) {

            node  = nextSibling( parent, node );

            if ( node != null ) {
                return oldNode;
            }

            node   = parent;
            parent = getParent.c(parent);
        }

        node = null;

        return oldNode;
    }

    public void remove() {
        throw new Error( "not impl" );
    }

    private T nextSibling( T parent, T node ) {

        boolean found = false;
        for ( T child : getChildren.c(parent) ) {
            if ( found ) {
                return child;
            }

            if ( child == node ) {
                found = true;
            }
        }

        assert( found );
        return null;
    }
}
