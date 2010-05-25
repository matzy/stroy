package org.openCage.gpad;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.localization.Localize;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

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

/**
 * A preference pane to select encoding schemes
 * TODO put in some life
 */
public class CodePrefBuilder implements PrefBuilder {

    private final Localize localize;

    @Inject
    public CodePrefBuilder( @Named( Constants.FAUSTERIZE) Localize localize) {
        this.localize = localize;
    }

    private class CodePref extends JPanel {

        public CodePref() {
            setLayout( new BorderLayout());
            add( new JLabel( "TODO" ), BorderLayout.CENTER  );

        }
    }


    @Override
    public JPanel getPrefPanel() {
        return new CodePref();
    }

    @Override
    public JButton getPrefButton() {
        JButton button = new JButton( localize.localize( "org.openCage.fausterize.code" ));
        button.setIcon( new ImageIcon( getClass().getResource( "code.png" )));

        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition( AbstractButton.CENTER);

        return button;
    }
}
