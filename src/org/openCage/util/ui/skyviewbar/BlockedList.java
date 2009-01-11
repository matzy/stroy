package org.openCage.util.ui.skyviewbar;

import org.openCage.util.logging.Log;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

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
