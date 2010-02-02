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
                textArea.setText( textEncoder.encode( textArea.getText(), 0 ));
                encoded = true;
            } else {
                // the text is decoded, but th wrong pad might have been used
                // so lets just read from disk
                
                setInitialText();
            }
        }
    }

    private boolean canWrite() {
        return file != null && pad != null && changed;
    }

    synchronized void write() {
        if ( canWrite() ) {
            // make sure the directory exists
            file.getParentFile().mkdirs();
            
            if ( encoded ) {
                try {
                    FileUtils.writeStringToFile( file, textArea.getText() );
                } catch (IOException e) {
                    // TODO
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } else {
                try {
                    FileUtils.writeStringToFile( file, textEncoder.encode( textArea.getText(), 0 ));
                } catch (IOException e) {
                    e.printStackTrace();  //TODO
                }
            }
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
    }
}
