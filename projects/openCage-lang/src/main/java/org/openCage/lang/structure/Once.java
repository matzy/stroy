package org.openCage.lang.structure;

import org.openCage.lang.Equals;

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


public class Once<T> {

    private T val;
    private boolean set = false;

    public Once( T deflt ) {
        val = deflt;
    }

    public final synchronized T get() {
        if ( !set ) {
            throw new IllegalAccessError( "once: get before set" );
        }
        return val;
    }

    public synchronized void set( T t ) {
        if ( set ) {
            if ( Equals.equals( val, t )) {
                return;
            }
            
            throw new IllegalStateException("can't set Once twice (old: " + val + " new: " + t + ")");
        }

        set = true;
        val = t;
    }

    public boolean isSet() {
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Once)) { return false; }

        Once once = (Once) o;

        if (val != null ? !val.equals(once.val) : once.val != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }

    public synchronized void setIf( Once<T> t ) {

        if ( !t.isSet() ) {
            return;
        }

        set( t.get() );
    }

    @Override
    public String toString() {
        return "Once{" + (set ? " " : "* ") + val + "}";
    }
}
