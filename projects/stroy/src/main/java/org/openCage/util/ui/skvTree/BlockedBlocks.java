package org.openCage.util.ui.skvTree;

import org.openCage.util.logging.Log;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.ArrayList;

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

public class BlockedBlocks<T> {

    private List<List<T>> src;
    private List<List<T>> reducedList = new ArrayList<List<T>>();
    private final int     stripSize;
    private int           fullSize;

    public BlockedBlocks( List<List<T>> src, int stripsize, int fullSize ) {
        this.src       = src;
        this.stripSize = stripsize;
        this.fullSize  = fullSize;

        recalc();
    }

    public void setSrc( List<List<T>> src ) {
        this.src = src;
        recalc();
    }

    public void setFullsize( int fullSize ) {
        this.fullSize  = fullSize;
        recalc();
    }

    private void recalc() {
        double incr = Math.min( ((double)fullSize) / src.size(), stripSize );
        double sum  = 0;
        reducedList.clear();

        for ( List<T> elem : src ) {
            add( (int)(sum / stripSize ), elem );
            sum += incr;
        }
    }

    private void add(int idx, List<T> elem) {
        if ( reducedList.size() - 1 == idx ) {
            reducedList.get( idx ).addAll( elem );
        } else if ( reducedList.size() == idx  ) {
            List<T> ll = new ArrayList<T>();
            ll.addAll( elem );
            reducedList.add( ll );
        } else {
            Log.warning( "blocklist: prog error" );
            throw new IllegalStateException( "BlockedBlocks: idx too large" );
        }
    }

    public List<T> get( int idx ) {
        return reducedList.get(idx);
    }

    public List<T> getByPoint( int idx ) {
        return reducedList.get( Math.min( idx / stripSize, reducedList.size() - 1 )); 
    }

    public List<List<T>> get() {
        return reducedList;
    }
}
