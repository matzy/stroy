package org.openCage.stroy.ui;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

// TODO modal ?
public class ModalProgress extends JDialog {

    private JProgressBar progress = new JProgressBar();
    private JLabel txt = new JLabel();

    public ModalProgress( JFrame frame ) {

        super( frame, false );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        progress.setIndeterminate( true );
        progress.setSize( 500, 10 );
        txt.setSize( 500, 10);

        layout.row().add( " ").add(progress, 70 );
        layout.row().add( " ").add(txt, 70 );


        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );

        setSize( 400, 30 );

        pack();
    }

    public void setText(String str) {
        txt.setText( str );
    }
}
