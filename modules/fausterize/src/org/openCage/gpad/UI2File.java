package org.openCage.gpad;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.FE0;
import org.openCage.localization.protocol.Localize;

import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class UI2File {

    private final JTextArea textArea;
    private final BackgroundExecutor      executor;
    private final TextEncoderIdx<String>  textEncoder  = new FaustString();
    private final Localize                localize;

    private File                     file;
    private URI                      pad;
    private boolean                  encoded;
    private boolean                  changed = false;

    public UI2File( @NotNull JTextArea area,
                    @NotNull BackgroundExecutor executor,
                    @NotNull Localize localize,
                    @NotNull File file ) {
        this.textArea = area;
        this.executor = executor;
        this.localize = localize;
        this.file = file;

        executor.addPeriodicAndExitTask( new FE0<Void>() {
            @Override
            public Void call() throws Exception {

                write();
                return null;
            }
        });

        textArea.addKeyListener( new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ( textArea.isEditable() && !changed ) {
                    changed = true;
                }
            }
        });

        init();
        setInitialText();
    }

    private void init() {
        pad = null;
        changed = false;
        textArea.setText("");
    }

    private void setInitialText() {

        if ( file.exists() ) {
            try {
                textArea.setText( FileUtils.readFileToString( file ));
                encoded = true;
                changed = false;
            } catch (IOException e) {
                e.printStackTrace();  //TODO
            }

            return;
        }

        textArea.append( localize.localize("org.openCage.fausterize.intro"));
        encoded = false;
    }


    public synchronized void codeToggle() {

        if ( pad == null ) {
            return;
        }

        if ( encoded ) {
            textArea.setText( textEncoder.decode( textArea.getText(), 0 ));
            encoded = false;
        } else {


            if ( changed ) {
                write();
                textArea.setText( textEncoder.encode( textArea.getText(), 0 ));
                encoded = true;
                changed = false;
            } else {
                // the text is decoded, but the wrong pad might have been used
                // so lets just read from disk
                init();
                setInitialText();
            }
        }
    }

    private boolean canWrite() {
        return file != null && pad != null && changed && !encoded;
    }

    synchronized void write() {
        if ( canWrite() ) {
            // make sure the directory exists
            file.getParentFile().mkdirs();
            
            if ( encoded ) { // TODO we should not get here
                try {
                    //System.out.println("writing " + toString());
                    FileUtils.writeStringToFile( file, textArea.getText() );
                } catch (IOException e) {
                    // TODO
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } else {
                try {
                    //System.out.println("writing " + toString());
                    FileUtils.writeStringToFile( file, textEncoder.encode( textArea.getText(), 0 ));
                } catch (IOException e) {
                    e.printStackTrace();  //TODO
                }
            }
        }
    }

    private synchronized void writeEncoded() {


        if ( file == null || !encoded ) {
            throw new IllegalStateException( "called in wrong context" );
        }

        // make sure the directory exists
        file.getParentFile().mkdirs();

        try {
            //System.out.println("writing " + toString());
            FileUtils.writeStringToFile( file, textArea.getText() );
        } catch (IOException e) {
            // TODO
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public void setPad( @NotNull URI pad ) {
        this.pad  = pad;
        textEncoder.setPad( pad);
    }

    public boolean isPadSet() {
        return pad != null;
    }


    public boolean isEncoded() {
        return encoded;
    }

    public void setFile( @NotNull File file ) {
        write();
        init();
        this.file = file;
        setInitialText();
    }

    public void changeFile(File file) {
        this.file = file;
        if ( encoded ) {
            writeEncoded();
        } else {
            // picked up by std write
            changed = true;
        }
    }

    @Override
    public String toString() {
        return "ui2file pad: " + pad + " file: " + file + " encoded " + encoded + " changed " + changed;
    }
}
