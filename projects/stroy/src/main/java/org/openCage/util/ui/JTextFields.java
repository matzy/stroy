package org.openCage.util.ui;

import org.openCage.stroy.ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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

public class JTextFields {

    public static boolean isFile( JTextField field, Color neutral, Color warning ) {

        if ( new File(field.getText()).exists() && new File(field.getText()).isFile() ) {
            field.setBackground( Colors.BACKGROUND_NEUTRAL );
            return true;            
        }
                
        String txt = field.getText();
        txt = txt.trim();

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
