package org.openCage.util.prefs;

import org.openCage.util.logging.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
 * A list class with a marker for a selected element
 */
public class ListSelection<T> {

    private  List<T> list;
    private  T       selection;

    public ListSelection( T sel, List<T> list  ) {
        this.list = new ArrayList<T>( list );
        setSelection( sel );
    }


    public ListSelection( T sel, T ... list ) {
        this( sel, Arrays.asList( list ));
    }



    public T getSelection() {
        return selection;
    }

    /**
     * set the selected element of the list
     * @param selection A valid element of the list
     */
    public void setSelection( T selection ) {
        if ( !list.contains( selection ) ) {
            Log.warning( "selection outside list: ignored" );
            throw new IllegalArgumentException( "selection outside list"  );
        }

        this.selection = selection;
    }


    public List<T> getList() {
        return Collections.unmodifiableList( list );
    }
}
