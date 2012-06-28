package org.openCage.lang.structure;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.annotations.HiddenCall;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
