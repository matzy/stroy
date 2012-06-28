package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.update.UpdatePrefs;
import org.openCage.stroy.RuntimeModule;

import javax.swing.*;
import java.awt.*;

import com.google.inject.Injector;
import com.google.inject.Guice;
import org.openCage.util.prefs.LocaleSelectionProperty5;
import org.openCage.util.prefs.LogLevelSelectionProperty5;

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

public class MorePrefs extends JPanel {

    public MorePrefs( final UpdatePrefs updatePrefs, final LocaleSelectionProperty5 localeSelection, LogLevelSelectionProperty5 logSelection) {
        setLayout( new GridBagLayout());

        GridBagConstraints cnstraint = new GridBagConstraints();

        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 1;
        cnstraint.weightx = 1.0;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( new LogPrefs(logSelection), cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 2;
        cnstraint.weightx = 1.0;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( updatePrefs, cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 3;
        cnstraint.weightx = 1.0;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( new LanguagePrefs( localeSelection ), cnstraint );
    }


}
