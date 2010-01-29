package org.openCage.gpad;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.FileUtils;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.FE0;
import org.openCage.localization.protocol.Localize;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 21, 2010
 * Time: 9:16:37 AM
 * To change this template use File | Settings | File Templates.
 */
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
                if ( textArea.isEditable() ) {
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
                textArea.append( FileUtils.readFileToString( file ));
                encoded = true;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
            textArea.setText( textEncoder.encode( textArea.getText(), 0 ));
            encoded = true;
        }
    }

    private boolean canWrite() {
        return file != null && pad != null && changed;
    }

    private synchronized void write() {
        if ( canWrite() ) {
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
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }


    public void setFile( @NotNull File file ) {
        write();
        init();
        this.file = file;
        setInitialText();
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
}
