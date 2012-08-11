package org.openCage.util.prefs;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.structure.ObservableRef;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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

public class LogLevelSelectionProperty5 extends ListSelectionProperty<Level> {

    @Inject
    public LogLevelSelectionProperty5( @Named(value = "loglevel") ObservableRef<Level> selection) {
        super("loglevels", getLevels(), selection);
    }

    public static List<Level> getLevels() {
        List<Level> list = new ArrayList<Level>();
        list.add( Level.OFF );
        list.add( Level.SEVERE );
        list.add( Level.WARNING );
        list.add( Level.CONFIG );
        list.add( Level.INFO );
        list.add( Level.FINE );
        list.add( Level.FINER );
        list.add( Level.FINEST );

        return list;
    }

}
