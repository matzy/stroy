package org.openCage.stroy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openCage.lang.structure.T2;
import org.openCage.stroy.locale.Message;
import net.java.dev.designgridlayout.DesignGridLayout;

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

        progress.setIndeterminate( true );

        layout.row().grid().add( label, 1 ).add( txt, 4 );
        layout.row().grid().empty(20);

        layout.row().grid().add(progress, 3 ).add( inBackground );

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
