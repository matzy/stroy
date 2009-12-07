package org.openCage.gpad;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

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
public class FausterizerUI extends JFrame {

    private JTextArea textUI = new JTextArea();
    TextEncoder<String> tts;

    public FausterizerUI(String pad, final String message ) {

        JButton save = new JButton("save");
        save.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new WithImpl().withWriter( new File(message), new FE1<Object, Writer>() {
                    public Object call(Writer writer) throws Exception {
                        System.out.println(textUI.getText());
                        String code = tts.encode( textUI.getText());
                        System.out.println( code );
                        writer.append( code );
                        return null;                        
                    }
                });
            }
        });

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( new JScrollPane(textUI), BorderLayout.CENTER  );
        getContentPane().add( save, BorderLayout.SOUTH );

        setTitle( "Fausterize" );
        setSize( 800, 600 );

        pack();

        try {
            tts = new TextToStory( new URI( pad ));

            File filem = new File(message);
            String text = "";

            if ( filem.exists() ) {
                FileLineIterable it =  new WithImpl().getLineIteratorCloseInFinal( filem );
                try {
                    for ( String str : it ) {
                        text += str + "\n";
                    }
                } finally {
                    it.close();
                }
                textUI.append( tts.decode( text ));                
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }



    public static void main(String[] args) {
        new FausterizerUI( args[0], args[1] ).setVisible(true);
    }
}
