package org.openCage.babel;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.ListenerControl;
import org.openCage.lang.Listeners;

import java.util.*;
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
public class LocalePreference {

    private ReentrantReadWriteLock  lock      = new ReentrantReadWriteLock( );
    private Listeners<List<Locale>> listeners = new Listeners<List<Locale>>();
    private List<Locale>            locales   = new ArrayList<Locale>();

    public LocalePreference() {
        locales.add( Locale.getDefault());
        locales.add( Locale.ENGLISH );
    }

    public List<Locale> getLocales() {
        lock.readLock().lock();
        try {
            return Collections.unmodifiableList( locales );
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setLocales(List<Locale> locales) {
        lock.writeLock().lock();
        try {
            this.locales.clear();
            this.locales.addAll( locales );
            //this.locales = locales;

            listeners.shout( locales );
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void setLocales(Locale ... locales ) {
        setLocales( Arrays.asList( locales ));
    }

    public ListenerControl<List<Locale>> getListenerControl() {
        return listeners.get();
    }
}
