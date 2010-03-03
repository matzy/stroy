package org.openCage.ui.impl.pref;

import com.google.inject.name.Named;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.*;

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
 * The preference panel for text editor preferences
 */
public class TextEditorPref implements PrefBuilder {

    private @Named( "ui" ) Localize localize;
    //private TextCursor textCursor;

    @Override
    public JPanel getPrefPanel() {
        return new TextEditorPrefPane();
    }

    @Override
    public JButton getPrefButton() {
        JButton button = new JButton( "Text" );
        button.setIcon( new ImageIcon( getClass().getResource( "texteditor.png" )));

        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition( AbstractButton.CENTER);

        return button;
    }

    private class TextEditorPrefPane extends JPanel {

        public TextEditorPrefPane() {
            DesignGridLayout layout = new DesignGridLayout( this );
            setLayout( layout );

            layout.row().label( new JLabel( "caret type" )).add( new JLabel( "block" ));
        }

    }
}
