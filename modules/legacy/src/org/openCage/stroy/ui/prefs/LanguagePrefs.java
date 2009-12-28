package org.openCage.stroy.ui.prefs;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.openCage.stroy.locale.Message;
import org.openCage.stroy.locale.LocalizedComboBox;

import java.awt.*;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class LanguagePrefs extends JPanel {

    public LanguagePrefs() {

        LocalizedComboBox languageBox = new LocalizedComboBox(Message.localKey);

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().add( new JLabel(""));
        layout.row().add( Message.getl( "Pref.language.which" ), 1 ).add(languageBox);
        layout.row().empty().add( Message.getl( "Pref.language.warning" ));

        top.setBorder( new TitledBorder( Message.get( "Pref.language.title" ) ));

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );


    }

}
