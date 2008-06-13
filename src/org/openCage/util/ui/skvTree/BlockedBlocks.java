package org.openCage.util.ui.skvTree;

import org.openCage.util.logging.Log;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.ArrayList;

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
