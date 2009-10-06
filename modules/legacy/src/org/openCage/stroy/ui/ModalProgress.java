package org.openCage.stroy.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import org.openCage.stroy.locale.Message;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.lang.protocol.tuple.T2;

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

// TODO modal ?
public class ModalProgress extends JDialog {

    private JProgressBar progress     = new JProgressBar();
    private JLabel       txt          = new JLabel();
    private JLabel       label        = new JLabel();
    private JButton      inBackground = new JButton( Message.get("Progress.doInBackground"));

    public ModalProgress( JFrame frame ) {

        super( frame, false );

        if ( frame == null ) {
            setLocation( 200, 100 );
        } else {
            Point loc = frame.getLocation();
            loc.move( 100, 50 );
            setLocation( loc ); 
        }


        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        progress.setIndeterminate( true );

        layout.row().add( label, 1 ).add( txt, 4 );
        layout.emptyRow(20);

        layout.row().add(progress, 3 ).add( inBackground );

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );

        pack();

        inBackground.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    public void setText( String labl, String str) {
        label.setText( labl );
        // TODO cut
        txt.setText( str );
    }

    protected void setText( T2<String,String> txt ) {
        if ( txt.i1 == null ) {
            setTitle( txt.i0 );
        } else {
            setText( txt.i0, txt.i1 );
        }
    }


    @Override
    public void setTitle( String title ) {
        setText( "", "" );
        super.setTitle( title );
    }
}
