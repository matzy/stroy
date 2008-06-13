package org.openCage.stroy.ui;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

import org.openCage.stroy.locale.Message;
import org.openCage.util.iterator.T2;

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

// TODO modal ?
public class ModalProgress extends JDialog {

    private JProgressBar progress = new JProgressBar();
    private JLabel txt = new JLabel();
    private JButton inBackground = new JButton( Message.get("Progress.doInBackground"));

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
        layout.row().add( new JLabel(" "), 10 ).add( inBackground, 5 );


        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );

        setSize( 400, 30 );

        pack();

        inBackground.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    public void setText(String str) {
        txt.setText( str );
    }

    protected void setText( T2<String,Boolean> txt ) {
        if ( txt.i1 ) {
            setTitle( txt.i0 );
        }
        else {
            setText( txt.i0 );
        }
    }


    @Override
    public void setTitle( String title ) {
        setText( "" );
        super.setTitle( title );
    }
}
