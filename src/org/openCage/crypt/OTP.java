package org.openCage.crypt;

import org.openCage.stroy.locale.Message;
import org.openCage.utils.io.with.WithIO;
import org.openCage.utils.io.with.ReaderFunctor;
import org.openCage.utils.io.with.InputStreamFunctor;
import org.openCage.utils.io.with.WriterFunctor;
import org.openCage.utils.func.F1;

import javax.swing.*;

import net.java.dev.designgridlayout.DesignGridLayout;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.net.URLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.*;
import java.nio.CharBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 27, 2009
 * Time: 9:41:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class OTP extends JFrame {
    private JTextField padFile = new JTextField( "http://" );
    private JButton fileButton = new JButton( "..." );
    private JTextArea txt = new JTextArea("");
    private  JScrollPane textScroll = new JScrollPane( txt );
    private JTextField txtFile = new JTextField("");
    private byte[] pad = new byte[1000000];
    private int padSize = 0;
    private int txtBytesSize = 0;
    private byte[] txtBytes = new byte[1000000];
    private File textFileFile = null;

    public static void main(String[] args) {
        new OTP().setVisible( true );
    }

    public OTP() {
        setTitle( "OTP");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 200 );

        txt.setRows( 30 );
        txt.setColumns( 100 );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().label( new JLabel( "pad") ).add( padFile, 8 );
        layout.row().label( new JLabel( "texFile") ).add( txtFile, 7 ).add( fileButton);
        layout.row().add( textScroll, 8 );


        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();

        padFile.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        padFile.addKeyListener( new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if ( ch == '\n' ) {
                    readPadFile();
                }
            }

            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        txtFile.addKeyListener( new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if ( ch == '\n' ) {
                    readTextFile();
                }
            }

            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        Runtime.getRuntime().addShutdownHook(
                new Thread( new Runnable() {
                    public void run() {
                        save();
                    }
                }));



    }

    private void save() {
        if ( textFileFile == null ) {
            return;
        }

        WithIO.withWriter( textFileFile.getAbsolutePath(), new WriterFunctor() {
            public void c(Writer writer) throws IOException {
                writer.write( txt.getText() );
            }
        });
        
    }

    private void readTextFile() {

        textFileFile = new File( txtFile.getText());

        if ( textFileFile.exists() ) {
            WithIO.withIS( textFileFile, new InputStreamFunctor<Void>() {
                public Void c(InputStream is) throws IOException {
                    txtBytesSize = is.read(txtBytes);
                    return null;
                }
            });
        }
    }

    private void readPadFile() {

        URL url = null;
        try {
            url = new URL( padFile.getText() );
        } catch (MalformedURLException e) {
            System.err.println("not a url: " + padFile.getText());
            return;
        }

        WithIO.withIS( url, new InputStreamFunctor<Void>() {
            public Void c(InputStream is) throws IOException {
                padSize = is.read(pad);
                return null;
            }
        });

        translateIf();
    }

    private void translateIf() {
        if ( padSize == 0 || txtBytesSize == 0 ) {
            return;
        }


        int pdx = 1001;

        for ( int idx = 0; idx < txtBytesSize; ++idx ) {
            if ( pdx > padSize ) {
                pdx = 0;
            }
            while( pad[pdx] == 0 ) {
                ++pdx;
                if ( pdx > padSize ) {
                    pdx = 0;
                }
            }

            txtBytes[idx] = (byte) ((int)txtBytes[idx] ^ (int)pad[pdx]);
            ++pdx;
        }

        txt.append( new String(txtBytes));
    }
}
