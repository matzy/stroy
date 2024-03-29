package org.openCage.util.ui;

import org.openCage.stroy.ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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

public class JTextFields {

    public static boolean isFile( JTextField field, Color neutral, Color warning ) {

        if ( new File(field.getText()).exists() && new File(field.getText()).isFile() ) {
            field.setBackground( Colors.BACKGROUND_NEUTRAL );
            return true;            
        }
                
        String txt = field.getText();
        txt.trim();

        if ( txt.startsWith( "\"")) {
            txt = txt.substring( 1, txt.indexOf( " ", 1));
        } else {
            int pos = txt.indexOf( " ");
            if ( pos > 0 ) {
                txt = txt.substring( 0, txt.indexOf( " "));
            }
        }


        File file = new File( txt );

        // TODO apps ?
        if ( file.exists() && file.isFile() ) {
            field.setBackground( Colors.BACKGROUND_NEUTRAL );
            return true;
        } else {
            field.setBackground( Colors.BACKGROUND_WARN);
            return false;
        }
    }
}
