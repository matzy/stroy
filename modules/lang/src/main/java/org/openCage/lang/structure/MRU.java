package org.openCage.lang.structure;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.annotations.HiddenCall;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
 * A Most Recently Used Collection ordered by last use
 * @param <T>
 */
@ThreadSafe
public class MRU<T> {
    private static final int INIT_SIZE = 10;

    private transient int maxSize = INIT_SIZE;
    private List<T> used = new ArrayList<T>();

    public synchronized Collection<T> getAll() {
        return Collections.unmodifiableCollection(used);
    }

    public synchronized void use( T t ) {
        used.remove( t );
        List<T> newUsed = new ArrayList<T>();
        newUsed.add( t );
        newUsed.addAll( used );
        used = newUsed;
        while( used.size() > maxSize ) {
            used.remove( maxSize );
        }
    }

    public synchronized void setMaxSize( int size ) {

        if ( size < 1 ) {
            throw new IllegalArgumentException( "MRU size must be at least 1 not: " + size );
        }

        this.maxSize = size;
        while( used.size() > maxSize ) {
            used.remove( maxSize );
        }
    }

    public synchronized void clear() {
        used.clear();
    }

    @SuppressWarnings({"RedundantThrowsDeclaration"})
    @HiddenCall
    private Object readResolve() throws ObjectStreamException {
        maxSize = Math.max(INIT_SIZE, used.size());
        return this;
    }



}
