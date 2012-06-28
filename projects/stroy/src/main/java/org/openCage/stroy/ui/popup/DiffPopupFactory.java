package org.openCage.stroy.ui.popup;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.comphy.ImmuProp;
import org.openCage.comphy.StringProperty;
import org.openCage.lang.inc.Str;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.file.FileTypes5;
import org.openCage.stroy.filter.IgnoreCentral5;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.prefs.PrefsUI;

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

public class DiffPopupFactory<T extends Content> {

    private final PrefsUI prefsUI;
    private final ImmuProp<Str> editor;
    private final ImmuProp<Str> diffProg;
    private final FileTypes5 fileTypes;
    private final IgnoreCentral5 central;

    @Inject
    public DiffPopupFactory(PrefsUI prefsUI, @Named(value = "Editor") ImmuProp<Str> editor, @Named("DiffProg") ImmuProp<Str> diffProg, FileTypes5 fileTypes, IgnoreCentral5 central) {
        this.prefsUI = prefsUI;
        this.editor = editor;
        this.diffProg = diffProg;
        this.fileTypes = fileTypes;
        this.central = central;
    }

    public DiffPopup get( final TreeMatchingTask<T> taskLeft,
                          final TreeMatchingTask<T> taskRight ) {

        return new DiffPopup( prefsUI, editor, diffProg, fileTypes, central, taskLeft, taskRight );
    }

}
