package org.openCage.lang.structure;

import org.openCage.lang.iterators.Count;

import java.util.ArrayList;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
