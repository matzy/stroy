package org.openCage.lang.structure;

import org.openCage.lang.count.Count;

import java.util.ArrayList;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * A stack with backward/forward navigation, inspired by browser histories
 * @param <T>
 */
public class BFStack<T> {

    private List<T> list = new ArrayList<T>();
    private int pos = -1;

    /**
     * Move forward in the history
     * @return the next element if there is one
     */
    public T forward() {
        if ( !hasForward() ) {
            throw new IllegalStateException( "no forward" );
        }

        pos++;
        return list.get(pos);
    }

    /**
     * Check if there is a next element
     * @return true iff there is a next element
     */
    public boolean hasForward() {
        return pos < list.size() - 1;
    }

    /**
     * Check if there is a previous element
     * @return true iff there is a previous element
     */
    public boolean hasBackward() {
        return pos > 0;
    }

    /**
     * Move backward in the history
     * @return returns the previous element if there is one
     */
    public T backward() {
        if ( !hasBackward() ) {
            throw new IllegalStateException( "no more backward" );
        }
        pos--;
        return list.get(pos);
    }

    /**
     * add a new element
     * deletes all forward positioned elemebts with this one
     * @param elem
     */
    public void push( T elem ) {
        if ( hasForward() ) {
            list = list.subList( 0, pos + 1);
        }
        list.add( elem );
        pos++;
    }

    /**
     * Get the current element
     * @return the current element
     */
    public T getCurrent() {
        if ( list.size() == 0 ) {
            throw new IllegalStateException( "nothing in stack yet" );
        }
        return list.get(pos);
    }

    /**
     * the size of the bfstack
     * @return the size of the bfstack
     */
    public int size() {
        return list.size();
    }

    /**
     * clears the stack
     */
    public void clear() {
        pos = -1;
        list.clear();
    }

    /**
     * check whether the stack has any elements
     * @return true iff there are no elements in the stack
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * all the elements in a string, highlighs the current element
     * @return
     */
    @Override public String toString() {

        String ret = "";

        for ( Count<T> t : Count.count(list )) {
            if ( t.idx() == pos ) {
                ret += ">>> ";
            }
            ret += t.obj().toString() + "\n";
        }

        return ret;
    }
}
