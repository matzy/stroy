package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.locale.Message;
import org.openCage.stroy.update.UpdatePrefs;
import org.openCage.stroy.RuntimeModule;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import zappini.designgridlayout.DesignGridLayout;
import com.google.inject.Injector;
import com.google.inject.Guice;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class MorePrefs extends JPanel {

    public MorePrefs() {
        setLayout( new GridBagLayout());

        GridBagConstraints cnstraint = new GridBagConstraints();

        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 1;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( new LogPrefs(), cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 2;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);

        Injector injector = Guice.createInjector( new RuntimeModule() );        
        add( injector.getInstance( UpdatePrefs.class ), cnstraint );

    }


}
