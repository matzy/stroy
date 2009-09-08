package org.openCage.util.prefs;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class TextField extends JTextField {

    private PreferenceItem<String> item;

    public TextField( final PreferenceItem<String> item ) {
        this.item = item;
        final TextField me = this;
        super.setText( item.get() );

        item.addListener( new PreferencesChangeListener<String>() {
            public void changed(String txt) {
                me.setTextSuper( txt );
            }
        });

        addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                // first make sure the key changed the text
                super.keyReleased( keyEvent );
                item.set( me.getText());
            }
        });
    }

    public TextField( String key, String init ) {
        this( PreferenceString.getOrCreate( key, init ));
    }

    public TextField( String key ) {
        this( PreferenceString.getOrCreate( key, "" ));
    }


    public void setText(String txt) {

        if ( txt.equals( super.getText() ) ) {
            return;
        }

        super.setText(txt);
        item.set( txt );
    }


    private void setTextSuper( String txt ) {
        if ( txt.equals( super.getText() ) ) {
            return;
        }

        super.setText( txt );
    }

    public PreferenceItem<String> getItem() {
        return item;
    }


    public void reset() {
        item.reset();
        super.setText( item.get());
    }
}
