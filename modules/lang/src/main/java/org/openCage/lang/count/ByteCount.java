package org.openCage.lang.count;

import java.util.Iterator;

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

public class ByteCount implements Iterable<ByteCount>, Iterator<ByteCount> {
    private int idx = -1;
    private final byte[] array;

    public ByteCount( byte[] array ) { //NOSONAR
        this.array = array;
        // ByteCount is just a wrapper over the array so it is intential to copy it and not to clone ir        
    }

    @Override                                                                      
    public boolean hasNext() {
        return idx < array.length - 1;
    }

    @Override
    public ByteCount next() {
        idx++;
        return this;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException( "no remove in ArrayCount" );
    }

    @Override
    public Iterator<ByteCount> iterator() {
        return this;
    }

    public Byte obj() {
        return array[idx];
    }

    public int idx() {
        return idx;
    }

    /**
     * returns true if it is the last element in the iteration
     * @return if it is the last element in the iteration
     */
    public boolean isLast() {
        return !hasNext();
    }

    /**
     * returns true if it is the first element in the iteration
     * @return if it is the first element in the iteration
     */
    public boolean isFirst() {
        return idx == 0;
    }


    public static ByteCount count( byte[] arr ) {
        return new ByteCount( arr );
    }

}
