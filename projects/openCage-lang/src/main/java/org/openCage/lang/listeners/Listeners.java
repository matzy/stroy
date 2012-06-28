package org.openCage.lang.listeners;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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


@ThreadSafe
public class Listeners<T> implements ListenerControl<T> {

    private ReentrantReadWriteLock lock      = new ReentrantReadWriteLock( );
    private List<F1<Void,T>>       listeners = new ArrayList<F1<Void,T>>();

    /**
     * Call all the listeners with the news
     * @param news The news
     */
    public void shout( T news ) {
        lock.readLock().lock();
        try {
            for ( F1<Void,T> listener : listeners  ) {
                listener.call( news );
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Add a listener
     * @param listener Any Listener
     */
    public void add( F1<Void,T> listener ) {
        lock.writeLock().lock();
        try {
            listeners.add( listener );
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(F1<Void,T> listener ) {
        lock.writeLock().lock();
        try {
            listeners.remove(listener);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeCompletely( F1<Void,T> listener) {
        lock.writeLock().lock();
        try {
            listeners.removeAll(Collections.singleton( listener));
        } finally {
            lock.writeLock().unlock();
        }
    }
    /**
     * Get a restricted version of a Listeners to be used outside the class holding the Listeners
     * @return A object allowing only adding listeners
     */
    public ListenerControl<T> get() {
        return this;
    }

}
