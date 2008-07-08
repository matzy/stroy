package org.openCage.util.prefs;

import org.openCage.util.logging.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

/**
 * A list class with a marker for a selected element
 */
public class ListSelection<T> {

    public  List<T> list;
    private T       selection;

    public ListSelection( List<T> list, T sel ) {
        this.list = new ArrayList<T>( list );
        setSelection( sel );
    }


    public ListSelection( T[] list, T sel ) {
        this( Arrays.asList( list), sel );
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
}
