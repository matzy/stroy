package org.openCage.util.prefs;

import org.openCage.lang.listeners.Observable;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;
import org.openCage.lang.structure.ObservableRef;
import org.openCage.ruleofthree.Property;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.Threes;

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

public class ListSelectionProperty<T>  {

    protected final List<T>      list;
    private ObservableRef<T>     selection;
    private final String name;

    public ListSelectionProperty(String name, List<T> list, ObservableRef<T> selection) {
        this.list = list;
        this.selection = selection;
        this.name = name;
    }

    public T getSelection() {
        return selection.get();
    }

    public void setSelection(T selection) {
        if ( !list.contains(selection)) {
            throw new IllegalArgumentException( "selection not in list" );
        }
        this.selection.set(selection);
    }


    public List<T> getList() {
        return Collections.unmodifiableList( list );
    }

    public ObservableRef<T> getProp() {
        return selection;
    }
}
