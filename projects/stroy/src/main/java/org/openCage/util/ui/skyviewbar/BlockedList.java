package org.openCage.util.ui.skyviewbar;

import org.openCage.util.logging.Log;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

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
public class BlockedList<T> {

    private final List<List<T>> reducedList = new ArrayList<List<T>>();
    private final List<T>       source;
    private final int           minBlockSize;
    private final int           maxBlockSize;
    private final int           yStart;
    private int                 height = 0;

    public BlockedList( List<T> source, int blocksize, int maxBlocksize, int yStart ) {
        this.source       = source;
        this.minBlockSize = blocksize;
        this.maxBlockSize = maxBlocksize;
        this.yStart       = yStart;
    }

    public List<T> get( int idx ) {
        return reducedList.get(idx);
    }

    public List<T> get( Point pt ) {

        int y = (int)pt.getY();
        int idx = (y-yStart) / getBlocksize();

        if ( idx >= reducedList.size() ) {
            reducedList.get( reducedList.size() - 1 );
        }

        return reducedList.get( idx );
    }

    public List<List<T>> get() {
        return reducedList;
    }

    public void setHeight( int height ) {
        if ( this.height == height ) {
            return;
        }

        this.height = height;
        recalc();
    }

    private void recalc() {

        int bl = getBlocksize();

        reducedList.clear();

        double incr = (double)height / source.size();
        double sum  = 0;

        for ( T elem : source ) {
            add( (int)(sum / getBlocksize()), elem );
            sum += incr;
        }
    }

    private void add(int idx, T elem) {
        if ( reducedList.size() - 1 == idx ) {
            reducedList.get( idx ).add( elem );
        } else if ( reducedList.size() == idx  ) {
            List<T> ll = new ArrayList<T>();
            ll.add( elem );
            reducedList.add( ll );
        } else {
            Log.severe( "blocklist: prog error" );
        }
    }

    public int getBlocksize() {
//        return (int)Math.min( maxBlockSize, Math.max( minBlockSize, Math.ceil( (double)height / source.size() )));
        return (int)Math.max( minBlockSize, Math.ceil( (double)height / source.size() ));
    }

}
