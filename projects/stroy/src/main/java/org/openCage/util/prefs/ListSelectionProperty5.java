package org.openCage.util.prefs;

import org.openCage.comphy.*;
import org.openCage.comphy.Readable;
import org.openCage.lang.inc.GHashMap;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Str;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;

import java.util.Collections;
import java.util.List;

import static org.openCage.comphy.Readables.R;
import static org.openCage.comphy.Readables.toReadable;
import static org.openCage.lang.inc.Strng.S;

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

public class ListSelectionProperty5<T> implements Property {

    private VoidListeners observers = new VoidListeners();
    protected final List<T>      list;
    private T selection;
    private final String name;

    public ListSelectionProperty5( String name , List<T> list, T selection) {
        this.list = list;
        this.selection = selection;
        this.name = name;
    }

    public T getSelection() {
        return selection;
    }

    public void setSelection(T selection) {
        if ( !list.contains(selection)) {
            throw new IllegalArgumentException( "selection not in list" );
        }
        this.selection = selection;
        observers.shout();
    }

    @Override
    public VoidListenerControl getListenerControl() {
        return observers;
    }

    @Override
    public Readable toReadable() {
        return R(new GHashMap<Str,Readable>().
                putF(S("selection"), Readables.toReadable( selection )).
                putF(S(name), Readables.toReadable( list)));
    }

    public List<T> getList() {
        return Collections.unmodifiableList( list );
    }
}
