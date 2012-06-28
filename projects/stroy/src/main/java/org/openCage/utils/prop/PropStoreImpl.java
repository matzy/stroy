package org.openCage.utils.prop;

import org.openCage.utils.persistence.Persistable;

import java.util.Map;
import java.util.HashMap;

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
public class PropStoreImpl implements PropStore, Persistable {

    private Map<String, Prop> store = new HashMap<String, Prop>();
    private boolean           dirty = false;


    public Prop get( String key ) {
        return store.get( key );
    }

    public void init( String key, Prop val ) {
        if ( store.get( key  ) != null ) {
            // exists already
            // TODO check typ
            return;
        }

        // new prop

        store.put( key, val );

        // notify me if it changes in the future
        val.addListener( new PropChangeListener() {
            public void propChanged( Object txt ) {
                dirty = true;
            }
        } );

        // needs to be saved
        dirty = true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setClean() {
        dirty = false;
    }
}
