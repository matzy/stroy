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

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 18, 2009
 * Time: 5:08:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FaustUI extends JFrame {

    private JTextArea textUI = new JTextArea();
    TextEncoderIdx<String> tts;

    public FaustUI(String pad, final String message ) {

        JButton save = new JButton("save");
        save.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new WithImpl().withWriter( new File(message), new FE1<Object, Writer>() {
                    public Object call(Writer writer) throws Exception {
                        System.out.println(textUI.getText());
                        String code = tts.encode( textUI.getText(),0);
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
            tts = new FaustString();
            tts.setPad(  new URI( pad ) );

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
                textUI.append( tts.decode( text, 0 ));                
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }



    public static void main(String[] args) {
        new FaustUI( args[0], args[1] ).setVisible(true);
    }
}
